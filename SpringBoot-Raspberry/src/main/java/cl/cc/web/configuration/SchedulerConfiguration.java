package cl.cc.web.configuration;

import cl.cc.web.service.logic.SendMessageTask;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 *
 * @author CyberCastle
 */
@Configuration
public class SchedulerConfiguration {

    @Bean
    public SendMessageTask sendMessageTask() {
        return new SendMessageTask();
    }

    @Bean
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean(SendMessageTask sendMessageTask) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setTargetObject(sendMessageTask);
        jobDetail.setTargetMethod("run");
        return jobDetail;
    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean) {
        CronTriggerFactoryBean cronTrigger = new CronTriggerFactoryBean();
        cronTrigger.setJobDetail(methodInvokingJobDetailFactoryBean.getObject());
        cronTrigger.setCronExpression("0/1 * * * * ?");
        return cronTrigger;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean cronTriggerFactoryBean) {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setTriggers(new Trigger[]{cronTriggerFactoryBean.getObject()});
        return scheduler;
    }
}
