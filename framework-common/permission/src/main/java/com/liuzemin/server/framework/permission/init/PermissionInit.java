package com.liuzemin.server.framework.permission.init;

import com.liuzemin.server.framework.model.annotation.Operation;
import com.liuzemin.server.framework.model.annotation.Resource;
import com.liuzemin.server.framework.model.context.ServicePermissionContext;
import com.liuzemin.server.framework.model.model.BaseService;
import com.liuzemin.server.framework.model.model.Permission;
import com.liuzemin.server.framework.model.model.ServicePermission;
import com.liuzemin.server.framework.permission.client.SecurityFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Component
public class PermissionInit implements ApplicationRunner {

    public static final Logger log = LoggerFactory.getLogger(PermissionInit.class);

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    private static String serviceKey;

    private static Set<String> serviceAuthorized = new HashSet<>();

    @Autowired
    private SecurityFeignClient securityFeignClient;

    private void initUserPermission(){
        //1.扫描权限点
        List<Permission> permissions = getResource();
        //2.权限点插入数据库
        securityFeignClient.saveUserPermissionList(serviceName, permissions);
    }

    private void initServicePermission(){
        BaseService baseService = new BaseService();
        baseService.setServiceName(serviceName);
        List<BaseService> baseServices = securityFeignClient.findBaseServiceList(serviceName);
        if (CollectionUtils.isEmpty(baseServices)) {
            baseServices = new ArrayList<>();
            baseService.setContextPath(contextPath);
            baseServices.add(baseService);
            securityFeignClient.insertBaseServiceList(baseServices);
        }

        ServicePermission servicePermission = new ServicePermission();
        servicePermission.setServiceName(serviceName);
        List<ServicePermission> servicePermissions = securityFeignClient.findServicePermissionList(serviceName);
        if (!CollectionUtils.isEmpty(servicePermissions)) {
            Set<String> set = new HashSet<>();
            for (ServicePermission permission : servicePermissions) {
                if (null == permission || StringUtils.isEmpty(permission.getAuthorized())) {
                    continue;
                }
                set.add(permission.getAuthorized());
            }
            ServicePermissionContext.setServiceAuthorized(set);
        }
    }

    private List<Permission> getResource(){
        List<Permission> permissionList = new ArrayList<>();
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
        String basePath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX+"/com/liuzemin/server/**/controller/*Controller.class";

        try {

            org.springframework.core.io.Resource[] resources = resourcePatternResolver.getResources(basePath);
            TypeFilter typeFilter = new AnnotationTypeFilter(Resource.class);

            for(org.springframework.core.io.Resource resource : resources ){

                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                if(typeFilter.match(metadataReader, metadataReaderFactory)){
                    String rId = "";
                    String rName = "";
                    AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
                    Map<String,Object> classMap = annotationMetadata.getAnnotationAttributes(Resource.class.getName());
                    for(Map.Entry<String,Object> entry : classMap.entrySet()){
                        //Resource anno = (Resource) entry.getValue();
                        if("value".endsWith(entry.getKey())){
                            rId = (String)entry.getValue();
                        }
                        if("desc".endsWith(entry.getKey())){
                            rName = (String)entry.getValue();
                        }
                    }

                    Set<MethodMetadata> set =annotationMetadata.getAnnotatedMethods(Operation.class.getName());
                    for(MethodMetadata methodMetadata : set){
                        Permission permission = new Permission();
                        permission.setrId(rId);
                        permission.setrName(rName);
                        Map<String,Object> methodMap = methodMetadata.getAnnotationAttributes(Operation.class.getName());
                        for(Map.Entry<String,Object> entry : methodMap.entrySet()){
                            //Operation anno = (Operation) entry.getValue();
                            if("value".endsWith(entry.getKey())){
                                permission.setPrId((String) entry.getValue());
                            }
                            if("desc".endsWith(entry.getKey())){
                                permission.setPrName((String) entry.getValue());
                            }
                            // permission.setPrId(anno.value());
                            //permission.setPrName(anno.desc());
                        }
                        permissionList.add(permission);
                    }
                }
            }
        }catch(Exception e){
            log.error("get permission resource error|", e);
        }
        return permissionList;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 初始化服务权限
        initServicePermission();

        //初始化用户权限
        initUserPermission();
    }
}
