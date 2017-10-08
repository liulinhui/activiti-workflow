package com.activiti.common.directive;

import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component("identityDirective")
public class IdentityDirective implements TemplateDirectiveModel {
    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        String email = map.get("userEmail") == null ? "" :  map.get("userEmail").toString();
        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_26);
        environment.setVariable("identity", builder.build().wrap(email));
        templateDirectiveBody.render(environment.getOut());
    }
}
