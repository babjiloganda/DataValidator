package com.data.validator.drools;

import java.util.HashMap;
import java.util.Map;

import com.data.validator.rule.RuleFetcher;
import com.data.validator.rule.RuleSet;
import com.data.validator.rule.Rule;

public class SampleRuleFetcher implements RuleFetcher {

    private Map<String, RuleSet> ruleSetMap = new HashMap<>();

    public SampleRuleFetcher() {
        final Rule rule = buildRule(
                "BrandID", "import com.data.validator.batch.Product\n"
                        + "import com.data.validator.response.Response\n"
                        + "\n"
                        + "rule \"isValidBrandId\"\n"
                        + "  when\n"
                        + "  $response : Response()\n"
                        + "  $poi: Product(brand_id == null || brand_id <= 0, $brand_id: brand_id.toString());\n"
                        + "  then\n"
                        + "  System.out.println(\"Invalid Brand Id \" + $brand_id);\n"
                        + "  $response.addError( \"brand_id\", \"Invalid Brand Id \", $brand_id);\n"
                        + "end\n");

        final Rule rule1 = buildRule(
                "Wkt", "import com.data.validator.batch.Product\n"
                        + "import com.data.validator.response.Response\n"
                        + "import com.databricks.labs.mosaic.core.geometry.MosaicGeometryJTS\n"
                        + "\n"
                        + "function Boolean validateWKT(String building_footprint_wkt) {\n"
                        + "return MosaicGeometryJTS.fromWKT(building_footprint_wkt).getGeometryType().equals(\"Polygon\");"
                        + "}\n"
                        + "rule \"isValidWkt\"\n"
                        + "  when\n"
                        + "  $response : Response()\n"
                        + "  $poi: Product(building_footprint_wkt != null, $building_footprint_wkt:building_footprint_wkt,eval(validateWKT(building_footprint_wkt)))\n"
                        + "  then\n"
                        + "  System.out.println(\"Valid WKT \" + $building_footprint_wkt);\n"
                        + "  $response.addError( \"building_footprint_wkt\", \"Valid WKT \", $building_footprint_wkt);\n"
                        + "end\n");

        RuleSet ruleSet = new RuleSet();
        ruleSet.setName("SampleRuleSet");
        ruleSet.addRule(rule);
        ruleSet.addRule(rule1);

        ruleSetMap.put("SampleRuleSet", ruleSet);

    }

    private static Rule buildRule(final String name, final String rawRule) {
        final Rule rule = new Rule();
        rule.setName(name);
        rule.setRawRule(rawRule);
        return rule;
    }

    @Override
    public RuleSet getRuleSet(final String ruleSetName) {
        return ruleSetMap.get(ruleSetName);
    }

}
