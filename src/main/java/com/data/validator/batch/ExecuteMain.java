package com.data.validator.batch;

import com.data.validator.drools.DroolsValidator;
import com.data.validator.drools.SampleRuleFetcher;
import com.data.validator.rule.ValidatorContext;

public class ExecuteMain {
    public static void main(String[] args) {

        SampleRuleFetcher sampleRuleFetcher = new SampleRuleFetcher();

        ValidatorContext context = new ValidatorContext();
        context.setRuleSetName("SampleRuleSet");
        context.setRuleFetcher(sampleRuleFetcher);

        DroolsValidator validator = new DroolsValidator(context);

        Product product = new Product();

        product.setBrand_id(1);
        product.setBrand_name("McD");
        product.setBuilding_footprint_wkt("POLYGON ((2.3542097165438065 48.87961402841884, 2.3542502343986484 48.87966960286789, 2.3542000289784184 48.87974564271594, 2.3543467898567343 48.879773541836684, 2.3544025936659807 48.879681940147805, 2.3543437671071104 48.879580949132276, 2.3542097165438065 48.87961402841884))");

        var valid = validator.validate(product);
        var vlid = validator.isValid(product);


        System.out.println("The Error Field is " + valid.getErrors().toString());
        System.out.println("The Error Field is " + vlid);
    }


}