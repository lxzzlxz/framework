package com.liuzemin.server.framework.datasource.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.*;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
// 事务生效
@EnableTransactionManagement
public class TransactionConfiguration {

    private static final Logger log = LoggerFactory.getLogger(TransactionConfiguration.class);

    private static final Integer TRANSACTION_TIME_OUT = 120;

    private static final String TRANSACTION_POINT_CUT_EXPRESSION = "execution( * com.liuzemin.server..impl.*Service.*(..)) and !@annotation(com.liuzemin.server.framework.model.annotation.NoTransaction)";


    @Bean("transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource, Environment env) {
        log.info("init transactionManager... ...");
        return new
                DataSourceTransactionManager(dataSource);
    }


    @Bean("txAdvice")
    public TransactionInterceptor txAdvice(DataSourceTransactionManager transactionManager) {
        log.info("init txAdvice... ...");

        NameMatchTransactionAttributeSource transactionAttributeSource = new NameMatchTransactionAttributeSource();

        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setTimeout(TRANSACTION_TIME_OUT);
        Map<String, TransactionAttribute> txMap = new HashMap<>(16);
        txMap.put("*", requiredTx);

        RuleBasedTransactionAttribute supportsTx = new RuleBasedTransactionAttribute();
        supportsTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);
        supportsTx.setReadOnly(true);

        txMap.put("find*", supportsTx);
        txMap.put("validate*", supportsTx);
        txMap.put("query*", supportsTx);
        txMap.put("is*", supportsTx);
        txMap.put("select*", supportsTx);
        txMap.put("get*", supportsTx);

        transactionAttributeSource.setNameMap(txMap);

        TransactionInterceptor transactionInterceptor = new TransactionInterceptor(transactionManager,
                transactionAttributeSource);
        return transactionInterceptor;
    }

    @Bean
    public Advisor txAdvisor(TransactionInterceptor txAdvice) {
        log.info("init tx... ...");
        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        pc.setExpression(TRANSACTION_POINT_CUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pc, txAdvice);
    }

//	@Bean
//	public Advisor securityAdvisor(SecurityInterceptor securityInterceptor) {
//		AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
//		pc.setExpression(TRANSACTION_POINT_CUT_EXPRESSION);
//		return new DefaultPointcutAdvisor(pc, securityInterceptor);
//	}

}
