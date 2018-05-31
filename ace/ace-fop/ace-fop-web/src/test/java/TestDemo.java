import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.huacainfo.ace.common.tools.XmlConverUtil;
import com.huacainfo.ace.fop.model.*;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by HuaCai008 on 2018/5/4.
 */
public class TestDemo {

    @Test
    public void test() {
        String body = "<wechat>\n" +
                "<first>first</first>\n" +
                "<keyword1>keyword1</keyword1>\n" +
                "<keyword2>keyword2</keyword2>\n" +
                "<keyword3>keyword3</keyword3>\n" +
                "<keyword4>keyword4</keyword4>\n" +
                "<keyword5>keyword5</keyword5>\n" +
                "<remark>remark</remark>\n" +
                "</wecaht>\n" +
                "<sms>\n" +
                "你猜猜我是谁?\n" +
                "</sms>";

        Map<String, Object> map = XmlConverUtil.xmltoLinkMap(body);


        System.out.println("111111111111111");
    }


    @Test
    public void test1() {
        String json = "{\"basicInfo\":{\"fullName\":\"啊啊啊啊\",\"realName\":\"王恩\",\"companyType\":\"2\",\"establishDate\":\"2018-05-10\",\"crewSize\":\"12\",\"registeredCapital\":\"200\",\"fixedAssets\":\"12\",\"workingCapital\":\"121\",\"address\":\"巴巴爸爸不\",\"ownSpace\":\"1222\",\"tenancySpace\":\"1212\",\"majorVariety\":\"121212\",\"laborContractNum\":\"34\",\"thirdLaborRelation\":\"\",\"socialInsuranceAddr\":\"湖南常德\",\"socialInsuranceNum\":\"\",\"accTaxAmount\":\"25002\",\"yearTaxAmount\":\"2144\"},\"legalPerson\":{\"sex\":\"2\",\"birthDate\":\"2018-02-01\",\"nativePlace\":\"湖南\",\"nationality\":\"汗\",\"political\":\"民众\",\"recruitmentDate\":\"2018-05-01\",\"education\":\"\",\"skillJobTitle\":\"啊啊啊c\",\"deptPost\":\"CAA\",\"societyPost\":\"喂喂喂\",\"postcode\":\"415000\",\"fax\":\"\",\"email\":\"lc151525@163.com\",\"resume\":\"第三方地方大幅度\",\"achievement\":\"没得\"},\"org\":[{\"orgType\":\"1\",\"companyOrgType\":\"12121\",\"peopleNum\":\"3434\",\"establishDate\":\"2018-05-17\",\"dutyPersonName\":\"张三\",\"dutyPersonPhone\":\"13566445512\"},{\"orgType\":\"2\",\"establishDate\":\"2018-05-03\",\"dutyPersonName\":\"洒洒水\",\"dutyPersonPhone\":\"13788445511\"}],\"contribute\":[{\"itemCode\":\"1\",\"itemName\":\"安排下岗职工再就业\",\"itemValue\":\"撒撒撒撒撒撒\"},{\"itemCode\":\"2\",\"itemName\":\"助学兴教\",\"itemValue\":\"\"},{\"itemCode\":\"3\",\"itemName\":\"帮困扶贫\",\"itemValue\":\"\"},{\"itemCode\":\"4\",\"itemName\":\"其他\",\"itemValue\":\"\"}]}";
        JSONObject jsonObj = JSON.parseObject(json);
        String company = jsonObj.getString("basicInfo");
        String org = jsonObj.getString("org");
        String person = jsonObj.getString("legalPerson");
        String contribute = jsonObj.getString("contribute");

        FopCompany obj11111 = JSON.parseObject(company, FopCompany.class);
        FopPerson person111111 = JSON.parseObject(person, FopPerson.class);


        Type type = new TypeReference<List<FopCompanyOrg>>() {
        }.getType();
        List<FopCompanyOrg> lis11111 = JSON.parseObject(org, type);

        Type type1 = new TypeReference<List<FopCompanyContribution>>() {
        }.getType();
        List<FopCompanyContribution> list111111 = JSON.parseObject(contribute, type1);

        System.out.println(company);
        System.out.println(person);
        System.out.println(contribute);
        System.out.println(org);
    }

}
