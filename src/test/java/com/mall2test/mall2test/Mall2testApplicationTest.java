package com.mall2test.mall2test;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Mall2testApplicationTest {

    private static WebDriver driver;
    private static WebElement element;


    private void beforeRun() {
        //设置驱动所在位置
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ZH\\AppData\\Local\\Programs\\Python\\Python37-32\\chromedriver.exe");
        //指定移动设备
        Map<String, String> mobileEmulation = new HashMap<String, String>();
        mobileEmulation.put("deviceName", "iPhone X");
        ChromeOptions chromeOptions = new ChromeOptions(); chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        driver = new ChromeDriver(chromeOptions);
        System.out.println("指定移动设备窗口：iPhone X");
    }

    @BeforeAll
    public static void beforeClass() {
        System.out.println("用例前执行打印本句！");
        System.out.println("每条Test用例是互不相干的");
        System.out.println("用例开始执行…………");
    }

    @AfterAll
    public static void afterClass() throws InterruptedException, IOException {
        System.out.println("所有测试用例运行完毕!");
        System.out.println("准备发送报告");

        /*Document doc = Jsoup.parse(new File("test-output/emailable-report.html"), "utf-8", "https://www.baidu.com");
        System.out.println("标题是:"+doc.title());
        Elements table = doc.getElementsByClass("param");
        System.out.println("表格数据是:"+table);
        System.out.println("==================================================");
        Elements tr = table.select("tr");
        System.out.println(tr);
        System.out.println("==================================================");
        Elements td = tr.select("td");
        System.out.println(td);
        String methodpassesNum = td.get(1).text();
        System.out.println("用例通过次数:"+methodpassesNum);
        String scenariospassedNum = td.get(2).text();
        System.out.println("方案通过次数:"+scenariospassedNum);
        String skippedNum = td.get(3).text();
        System.out.println("用例跳过次数:"+skippedNum);
        String failedNum = td.get(4).text();
        System.out.println("用例失败次数:"+failedNum);
        String totalTime = td.get(5).text();
        System.out.println("用例总共耗时:"+totalTime);

        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
        Date date = new Date();// 获取当前时间*/

//        SendResult sendResult = DingTalk.custom()
//                .setUrl("https://oapi.dingtalk.com/robot/send?access_token=0517e6c24592e769f16d74b76f38918fe9b9452db448de22f03b81d38faf40f2")
//                .setMessage("每日例行测试结果如下:测试脚本TestMall:"+"\n"
//                        +"开始执行时间为:"+sdf.format(date) + "\n"
//                        +"  1.用例通过次数:"+methodpassesNum +"\n"
//                        +"  2.方案通过次数:"+scenariospassedNum +"\n"
//                        +"  3.用例跳过次数:"+skippedNum +"\n"
//                        +"  4.用例失败次数:"+failedNum +"\n"
//                        +"  5.用例总共耗时:"+totalTime +"\n"
//                        +"#报告地址#: http://10.1.6.67:1024/TestMall/emailable-report.html\n")
//                .send();

//        System.out.println(JSONObject.toJSONString(sendResult));
        System.out.println("发送报告成功!");
    }

    private void backhome() throws InterruptedException {
        driver.navigate().back();
        System.out.println("返回页面");
        Thread.sleep(1000);
    }

    /**
     * 点击事件
     * @param xpaht
     * @param msg
     */
    private void clickBtn(String xpaht, String msg) throws InterruptedException {
        driver.findElement(By.xpath(xpaht)).click();
        System.out.println(msg);
        Thread.sleep(1000);
    }

    /**
     * 下滑
     * @param xpath
     */
    private void slidedown(String xpath) throws InterruptedException {
        element = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        Thread.sleep(1000);
        System.out.println("下滑");
    }

    /**
     * 检测地址
     * @param xpath
     */
    private void checkUrl(String xpath) {
        WebDriverWait wait = (new WebDriverWait(driver, 20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        System.out.println("check页面");
    }


    /**
     * 测试首页链接的跳转
     * @throws InterruptedException
     */
    @Test
    @Tag("测试链接跳转")
    public void test_link() throws InterruptedException {
        System.out.println("====================test_link开始====================");
        beforeRun();
        //打开商场
        driver.get("http://xiehome.com/index.php/m.php");
        checkUrl("/html/body/div[2]/div[2]/div/img");

        driver.findElement(By.cssSelector("body > div.notice-agreement > div.index-dialog > a > img")).click();
        System.out.println("点击窗口海报进行跳转");
        checkUrl("/html/body/div[3]/div[2]/div");

        backhome();
        Thread.sleep(3000);
        checkUrl("//*[@id=\"index-ad-wrapper\"]/div[1]");

        driver.findElement(By.cssSelector("#index-ad-wrapper > div:nth-child(1)")).click();
        System.out.println("点击首页轮播图片");
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"seller_layout\"]/div[1]")));
        Thread.sleep(3000);
        System.out.println("断言页面");

        backhome();
        checkUrl("//*[@id=\"index-ad-wrapper\"]/div[1]");

        driver.findElement(By.cssSelector("#home_layout > div.home-menu > div > div.home-menu-content.home-menu-content-active > a:nth-child(1) > div")).click();
        System.out.println("点击找装修");
        checkUrl("//*[@id=\"seller_layout\"]/div[1]");

        backhome();
        checkUrl("//*[@id=\"index-ad-wrapper\"]/div[1]");

        driver.findElement(By.cssSelector("#home_layout > div:nth-child(7) > div.home-hot-box-body > div.home-hot-box-body-right > a:nth-child(1) > div > img")).click();
        System.out.println("点击热门头条");
        checkUrl("/html/body/div/div[2]");

        backhome();
        checkUrl("//*[@id=\"index-ad-wrapper\"]/div[1]");

        slidedown("//*[@id=\"home_layout\"]/div[5]/div[1]/div[1]");

        driver.findElement(By.cssSelector("#home_layout > div:nth-child(8) > div.home-warp-box-body > div:nth-child(1) > a > img")).click();
        System.out.println("点击大师装修");
        checkUrl("/html/body/div[5]/div/div[1]/span");

        backhome();
        checkUrl("//*[@id=\"index-ad-wrapper\"]/div[1]");

        slidedown("//*[@id=\"home_layout\"]/div[6]/div[1]/div[1]");

        driver.findElement(By.cssSelector("#home_layout > div:nth-child(9) > div.home-warp-box-body > div:nth-child(1) > a > img")).click();
        System.out.println("点击乐家");
        checkUrl("//*[@id=\"seller_layout\"]/div[6]/a[1]/div/div[2]/div[1]");

        backhome();
        checkUrl("//*[@id=\"index-ad-wrapper\"]/div[1]");

        slidedown("//*[@id=\"home_layout\"]/div[6]/div[2]/div[9]");

        driver.findElement(By.cssSelector("#activity-swiper > div.home-activity-box.layui-this > div > a:nth-child(1) > div > div.home-main-box-item-content > div.home-main-box-item-price > span.buy")).click();
        System.out.println("点击立即秒杀");
        checkUrl("//*[@id=\"bigSales_detail_layout\"]/div/div[7]/div[1]/div[2]/div[1]");

        backhome();
        checkUrl("//*[@id=\"index-ad-wrapper\"]/div[1]");

        slidedown("//*[@id=\"home_layout\"]/div[6]/div[2]/div[9]");

        driver.findElement(By.cssSelector("#home_layout > div:nth-child(12) > div.home-main-box-header > div:nth-child(2)")).click();
        System.out.println("点击团购更省");
        checkUrl("//*[@id=\"activity-swiper\"]/div[2]/div/a[1]/div/div[2]/div[3]/span[2]");
        driver.findElement(By.cssSelector("#activity-swiper > div.home-activity-box.layui-this > div > a:nth-child(1) > div > div.home-main-box-item-content > div.home-main-box-item-price > span.buy")).click();
        System.out.println("点击团购更省");
        checkUrl("//*[@id=\"groupBuy_detail_layout\"]/div/div[12]/div[1]/div[1]/div[2]");

        backhome();
        checkUrl("//*[@id=\"index-ad-wrapper\"]/div[1]");

        slidedown("//*[@id=\"home_layout\"]/div[6]/div[2]/div[9]");

        driver.findElement(By.cssSelector("#home_layout > div:nth-child(12) > div.home-main-box-header > div:nth-child(3)")).click();
        System.out.println("点击助力有礼");
        checkUrl("//*[@id=\"activity-swiper\"]/div[3]/div/div[1]/div[2]/div[3]/span[2]");
        driver.findElement(By.cssSelector("#activity-swiper > div.home-activity-box.layui-this > div > div:nth-child(1) > div.home-main-box-item-content > div.home-main-box-item-price > span.buy.laxin")).click();
        System.out.println("点击助力有礼");
        checkUrl("//*[@id=\"form\"]/div[2]/div[3]/a/p");

        backhome();
        checkUrl("//*[@id=\"index-ad-wrapper\"]/div[1]");

        slidedown("//*[@id=\"activity-swiper\"]/div[1]/div/a[4]/div/span");
        Thread.sleep(3000);
        slidedown("//*[@id=\"tg_container\"]/div[1]/div");

        driver.findElement(By.cssSelector("#tg_container > div.home-product-list.common-data > a:nth-child(4) > div > div.home-product-item-icon > img")).click();
        System.out.println("点击索菲亚衣柜");
        checkUrl("/html/body/div[5]/div/div[1]/span");

        Thread.sleep(3000);
        backhome();
        checkUrl("//*[@id=\"index-ad-wrapper\"]/div[1]");

//        driver.findElement(By.xpath("//*[@id=\"home_layout\"]/div[8]/a/img")).click();
//        System.out.println("点击右下角悬浮图片");
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[5]/div/div[1]/span")));
//        System.out.println("/html/body/div/div[5]/div[2]/div[2]");
//
//        driver.navigate().back();
//        System.out.println("返回页面");
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"index-ad-wrapper\"]/div[1]")));
//        System.out.println("断言页面");
        System.out.println("====================test_link结束====================");
        driver.close();
    }

    /**
     * 测试登陆功能
     * @throws InterruptedException
     */
    @Test
    @Tag("测试登陆功能")
    public void test_login() throws InterruptedException{
        System.out.println("====================test_login开始====================");
        beforeRun();
        //打开商场
        driver.get("http://www.xiehome.com/index.php/m.php/loginpage");
        System.out.println("访问登陆页面");
        checkUrl("//*[@id=\"form\"]/div[2]/div[3]/a/p");

        driver.findElement(By.id("phoneNum_login")).sendKeys("15818376789");
        System.out.println("输入正确账号");
        driver.findElement(By.id("login_password_num")).sendKeys("asd1234561");
        System.out.println("输入错误密码");

        driver.findElement(By.id("login_btn")).click();
        System.out.println("点击登陆");
        checkUrl("//*[@id=\"tips-shade\"]/div");
        System.out.println("验证:密码错误");

        Thread.sleep(3000);

        driver.findElement(By.id("phoneNum_login")).clear();
        System.out.println("清除登陆账号");
        driver.findElement(By.id("phoneNum_login")).sendKeys("15818376777");
        System.out.println("输入错误账号");
        driver.findElement(By.id("login_password_num")).clear();
        System.out.println("清除登陆密码");
        driver.findElement(By.id("login_password_num")).sendKeys("asd123456");
        System.out.println("输入正确密码");

        driver.findElement(By.id("login_btn")).click();
        System.out.println("点击登陆");
        checkUrl("//*[@id=\"tips-shade\"]/div");
        System.out.println("验证:密码错误");

        Thread.sleep(3000);
        driver.findElement(By.id("phoneNum_login")).clear();
        System.out.println("清除登陆账号");
        driver.findElement(By.id("phoneNum_login")).sendKeys("15818376789");
        System.out.println("输入正确账号");

        Thread.sleep(3000);
        driver.findElement(By.id("login_btn")).click();
        System.out.println("点击登陆");

        checkUrl("/html/body/div[2]/div[2]/div/img");

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/img")).click();

        System.out.println("====================test_login结束====================");
        driver.close();
    }

    /**
     * 测试订单功能
     * @throws InterruptedException
     */
    @Test
    @Tag("测试订单功能")
    public void test_order() throws InterruptedException{
        System.out.println("====================test_order开始====================");
        beforeRun();

        driver.get("http://www.xiehome.com/index.php/m.php/loginpage");
        System.out.println("打开登陆页面");
        driver.findElement(By.id("phoneNum_login")).sendKeys("15818376789");
        System.out.println("输入账号");
        driver.findElement(By.id("login_password_num")).sendKeys("asd123456");
        System.out.println("输入密码");
        driver.findElement(By.id("login_btn")).click();
        System.out.println("点击登陆");
        Thread.sleep(3000);
        System.out.println("等待3秒");

        clickBtn("/html/body/div[2]/div[2]/div/img","关闭广告弹出框");

        clickBtn("/html/body/div[5]/a[5]","点击我的");

        clickBtn("//*[@id=\"personal_layout\"]/div[3]/div[2]/a[1]","点击全部订单");

        clickBtn("//*[@id=\"myorder-box-top\"]/div[2]","点击待付款");

        clickBtn("//*[@id=\"myorder-box-top\"]/div[3]","点击待发货");

        clickBtn("//*[@id=\"myorder-box-top\"]/div[4]","点击待收货");

        clickBtn("//*[@id=\"myorder-box-top\"]/div[5]","点击已完成");

        clickBtn("//*[@id=\"myorder-box-top\"]/div[6]","点击退款/售后");

        clickBtn("//*[@id=\"myorder-box-top\"]/div[1]","点击全部订单");

        clickBtn("//*[@id=\"tg_container\"]/div[1]/div[1]","点击订单详情");

        slidedown("/html/body/div[5]/div/div[6]/p");

        clickBtn("//*[@id=\"header-right\"]","点击菜单");

        Thread.sleep(3000);
        System.out.println("等待3秒");

        clickBtn("//*[@id=\"alls-box\"]/div/div[2]/div[4]/a","点击我的");

        clickBtn("//*[@id=\"personal_layout\"]/div[3]/div[1]/div[2]/span","点击我的积分商城");

        Thread.sleep(3000);
        System.out.println("等待3秒");

        clickBtn("//*[@id=\"personal_layout\"]/div[3]/div[3]/a[1]","点击全部订单");

        clickBtn("//*[@id=\"myintegral-box-top\"]/div[2]","点击待付款");

        clickBtn("//*[@id=\"myintegral-box-top\"]/div[3]","点击待发货");

        clickBtn("//*[@id=\"myintegral-box-top\"]/div[4]","点击待收货");

        clickBtn("//*[@id=\"myintegral-box-top\"]/div[5]","点击退款/售后");

        System.out.println("====================test_order结束====================");
        driver.close();
    }

    /**
     * 测试分类功能
     * @throws InterruptedException
     */
    @Test
    @Tag("测试分类功能")
    public void test_sort() throws InterruptedException{
        System.out.println("====================test_sort开始====================");
        beforeRun();
        driver.get("http://www.xiehome.com/");
        System.out.println("打开登陆页面");

        clickBtn("/html/body/div[2]/div[2]/div/img","关闭弹窗");

        clickBtn("/html/body/div[5]/a[2]/div","点击分类");

        clickBtn("//*[@id=\"tg_container\"]/div/a[1]/div/div","点击全部");

        clickBtn("/html/body/div[1]/a[1]","点击返回");

        clickBtn("//*[@id=\"classify_layout\"]/div[1]/a[2]/div/span","点击装修公司");

        clickBtn("//*[@id=\"tg_container\"]/div/a[2]/div","点击半包");

        clickBtn("/html/body/div[1]/a[1]","点击返回");

        clickBtn("//*[@id=\"classify_layout\"]/div[1]/a[3]/div/span","点击建材超市");

        clickBtn("//*[@id=\"tg_container\"]/div/a/div","点击全部");

        clickBtn("/html/body/div[1]/a[1]","点击返回");

        clickBtn("//*[@id=\"classify_layout\"]/div[1]/a[4]/div/span","全屋定制");

        clickBtn("//*[@id=\"tg_container\"]/div/a/div","点击全部");

        clickBtn("/html/body/div[1]/a[1]","点击返回");

        clickBtn("//*[@id=\"classify_layout\"]/div[1]/a[5]/div/span","瓷砖");

        clickBtn("//*[@id=\"tg_container\"]/div/a[2]/div","地面砖");

        clickBtn("/html/body/div[1]/a[1]","点击返回");

        clickBtn("//*[@id=\"classify_layout\"]/div[1]/a[6]/div/span","点击卫浴洁具");

        clickBtn("//*[@id=\"tg_container\"]/div/a[3]/div/div/img","点击水龙头");

        clickBtn("/html/body/div[1]/a[1]","点击返回");

        clickBtn("//*[@id=\"classify_layout\"]/div[1]/a[7]/div/span","点击门窗");

        clickBtn("//*[@id=\"tg_container\"]/div/a[4]/div","点击玻璃门");

        clickBtn("/html/body/div[1]/a[1]","点击返回");

        clickBtn("//*[@id=\"classify_layout\"]/div[1]/a[8]/div/span","点击吊顶");

        clickBtn("//*[@id=\"tg_container\"]/div/a[4]/div","点击厨卫吊顶");

        clickBtn("/html/body/div[1]/a[1]","点击返回");

        clickBtn("//*[@id=\"classify_layout\"]/div[1]/a[9]/div/span","点击地板");

        clickBtn("//*[@id=\"tg_container\"]/div/a[5]/div","点击实木地板");

        clickBtn("/html/body/div[1]/a[1]","点击返回");

        slidedown("//*[@id=\"classify_layout\"]/div[1]/a[8]/div");

        clickBtn("//*[@id=\"classify_layout\"]/div[1]/a[10]/div/span","家私");

        clickBtn("//*[@id=\"tg_container\"]/div/a[5]/div","沙发");

        backhome();

        slidedown("//*[@id=\"classify_layout\"]/div[1]/a[8]/div");

        clickBtn("//*[@id=\"classify_layout\"]/div[1]/a[11]/div/span","窗帘墙纸");

        clickBtn("//*[@id=\"tg_container\"]/div/a[1]/div","全部");

        backhome();

        slidedown("//*[@id=\"classify_layout\"]/div[1]/a[8]/div");

        clickBtn("//*[@id=\"classify_layout\"]/div[1]/a[12]/div/span","家电");

        clickBtn("//*[@id=\"tg_container\"]/div/a[3]/div","智能家电");

        clickBtn("/html/body/div[1]/a[1]","点击返回");

        slidedown("//*[@id=\"classify_layout\"]/div[1]/a[8]/div");

        clickBtn("//*[@id=\"classify_layout\"]/div[1]/a[13]/div/span","环保");

        clickBtn("//*[@id=\"tg_container\"]/div/a/div","全部");

        backhome();

        slidedown("//*[@id=\"classify_layout\"]/div[1]/a[8]/div");

        clickBtn("//*[@id=\"classify_layout\"]/div[1]/a[14]/div/span","床上用品");

        clickBtn("//*[@id=\"tg_container\"]/div/a[3]/div","乳胶床垫");

        backhome();

        slidedown("//*[@id=\"classify_layout\"]/div[1]/a[8]/div");

        clickBtn("//*[@id=\"classify_layout\"]/div[1]/a[15]/div/span","灯饰照明");

        clickBtn("//*[@id=\"tg_container\"]/div/a[5]/div","LED灯");

        backhome();

        slidedown("//*[@id=\"classify_layout\"]/div[1]/a[8]/div");

        clickBtn("//*[@id=\"classify_layout\"]/div[1]/a[16]/div/span","油漆涂料");

        clickBtn("//*[@id=\"tg_container\"]/div/a[4]/div","艺术涂料");

        backhome();

        slidedown("//*[@id=\"classify_layout\"]/div[1]/a[8]/div");

        clickBtn("//*[@id=\"classify_layout\"]/div[1]/a[17]/div/span","其他");

        clickBtn("//*[@id=\"tg_container\"]/div/a/div","全部");

        System.out.println("====================test_sort结束====================");
        driver.close();
    }

    /**
     * 测试用户中心
     * @throws InterruptedException
     */
    @Test
    @Tag("测试用户中心")
    public void test_admin() throws InterruptedException {
        System.out.println("====================test_admin开始====================");
        beforeRun();
        driver.get("http://www.xiehome.com/");
        System.out.println("打开登陆页面");
        clickBtn("/html/body/div[2]/div[2]/div/img","关闭弹窗");

        clickBtn("/html/body/div[5]/a[5]","点击我的");

        checkUrl("//*[@id=\"form\"]/div[2]/div[3]/a/p");

        driver.findElement(By.id("phoneNum_login")).sendKeys("15818376789");
        System.out.println("输入正确账号");

        driver.findElement(By.id("login_password_num")).sendKeys("asd123456");
        System.out.println("输入正确密码");

        driver.findElement(By.id("login_btn")).click();
        System.out.println("点击登陆");

        Thread.sleep(5000);

        clickBtn("//*[@id=\"personal_layout\"]/div[1]/a/div/img","点击右上角设置");

        clickBtn("//*[@id=\"set-site\"]/a[1]","点击收货管理");

        clickBtn("//*[@id=\"showTooltips\"]","新添地址");

        driver.findElement(By.id("address_edit_name")).sendKeys("骆骆骆");
        Thread.sleep(2000);
        driver.findElement(By.className("address_edit_num")).sendKeys("15818376789");
        Thread.sleep(2000);
        driver.findElement(By.id("city-picker")).clear();
        Thread.sleep(2000);
        driver.findElement(By.id("city-picker")).sendKeys("广东省 东莞市 莞城区");
        Thread.sleep(2000);
        driver.findElement(By.className("address_edit_others")).sendKeys("东纵路愉景大厦三楼");
        Thread.sleep(2000);
        clickBtn("//*[@id=\"showTooltips\"]","点击保存");


        backhome();
        backhome();
        backhome();

        clickBtn("//*[@id=\"personal_layout\"]/div[1]/a/div/img","点击右上角设置");

        clickBtn("//*[@id=\"set-reset\"]/a[1]","点击密码重置");

        clickBtn("//*[@id=\"set-resetOldPW\"]/a[1]","验证旧密码");

        driver.findElement(By.id("former")).sendKeys("asd123456");

        driver.findElement(By.id("fresh")).sendKeys("asd654321");

        driver.findElement(By.id("affirm")).sendKeys("asd654321");


        driver.findElement(By.id("reset-box-bottom")).click();
        Thread.sleep(3000);

        clickBtn("//*[@id=\"personal_layout\"]/div[1]/a/div/img","点击右上角设置");

        clickBtn("//*[@id=\"set-reset\"]/a[1]","点击密码重置");

        clickBtn("//*[@id=\"set-resetOldPW\"]/a[1]","验证旧密码");

        driver.findElement(By.id("former")).sendKeys("asd654321");

        driver.findElement(By.id("fresh")).sendKeys("asd123456");

        driver.findElement(By.id("affirm")).sendKeys("asd123456");

        driver.findElement(By.id("reset-box-bottom")).click();
        Thread.sleep(3000);

        clickBtn("//*[@id=\"personal_layout\"]/div[1]/a/div/img","点击右上角设置");

        clickBtn("//*[@id=\"set-resetPhone\"]/a[1]/div/span","更换手机号");

        clickBtn("//*[@id=\"set-resetPW\"]/a[1]","验证密码");

        driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[1]/div/input")).sendKeys("asd123456");
        Thread.sleep(3000);

        clickBtn("/html/body/div[5]/div[2]/div/div[2]","点击下一步");

        backhome();
        backhome();

        driver.close();

        System.out.println("====================test_admin结束====================");
    }

    /**
     * 测试购物功能
     * @throws InterruptedException
     */
    @Test
    @Tag("测试购物功能")
    public void test_shopping() throws InterruptedException {
        System.out.println("====================test_shopping开始====================");
        beforeRun();
        driver.get("http://www.xiehome.com/");

        System.out.println("打开登陆页面");

        clickBtn("/html/body/div[2]/div[2]/div/img","关闭弹窗");

        clickBtn("/html/body/div[5]/a[3]","点击商家");

        clickBtn("//*[@id=\"tg_container\"]/div[1]/a[1]","点击苏宁异构");

        clickBtn("//*[@id=\"seller-home-warp-fitment-box\"]/div[1]/a[1]/div/div/div[2]/div[4]","点击立即购买");

        clickBtn("/html/body/div[5]/div/div[2]/span","点击立即购买");

        clickBtn("/html/body/div[6]/div[2]/div[4]/div[2]","点击立即购买");

        Thread.sleep(5000);

        driver.findElement(By.id("phoneNum_login")).sendKeys("15818376789");
        System.out.println("输入正确账号");

        driver.findElement(By.id("login_password_num")).sendKeys("asd123456");
        System.out.println("输入正确密码");

        driver.findElement(By.id("login_btn")).click();
        System.out.println("点击登陆");

        Thread.sleep(5000);

        clickBtn("/html/body/div[5]/div/div[2]/span","点击立即购买");

        clickBtn("/html/body/div[6]/div[2]/div[4]/div[2]","点击立即购买");

//        clickBtn("//*[@id=\"tg_container\"]/div/div[1]/input","选中商品");

//        clickBtn("//*[@id=\"cart_layout\"]/div[3]/div[2]","点击结算");

        driver.findElement(By.xpath("/html/body/div[5]/div/form/div[1]/div[4]/div[2]/textarea")).sendKeys("随便留点盐");

        clickBtn("/html/body/div[5]/div/form/div[1]/div[5]/button/div","点击结算");

        clickBtn("//*[@id=\"pay_select_layout\"]/div[2]/label[1]","选中微信支付");

        clickBtn("//*[@id=\"pay_select_layout\"]/div[3]","点击确认支付");

        checkUrl("/html/body/div/div[2]/h4");

        driver.close();

        beforeRun();

        driver.get("http://www.xiehome.com/");

        System.out.println("打开登陆页面");

        clickBtn("/html/body/div[2]/div[2]/div/img","关闭弹窗");

        clickBtn("/html/body/div[5]/a[5]","点击我的");

        driver.findElement(By.id("phoneNum_login")).sendKeys("15818376789");
        System.out.println("输入正确账号");

        driver.findElement(By.id("login_password_num")).sendKeys("asd123456");
        System.out.println("输入正确密码");

        driver.findElement(By.id("login_btn")).click();
        System.out.println("点击登陆");

        Thread.sleep(5000);

        clickBtn("//*[@id=\"personal_layout\"]/div[3]/div[2]/a[2]","待付订单");

        clickBtn("//*[@id=\"tg_container\"]/div/div[1]/div[2]/div","取消订单");

        clickBtn("/html/body/div[6]/div/div[2]/div[2]","取消");

        driver.close();

        System.out.println("====================test_shopping结束====================");
    }


}
