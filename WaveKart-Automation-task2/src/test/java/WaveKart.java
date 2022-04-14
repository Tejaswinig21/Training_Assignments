import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaveKart extends AnnotationMethods{

    @Test
    public void login(){

        webDriver.get("http://pkhw22ds2xds.cloud.wavemakeronline.com/WaveKart/#/Main");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        webDriver.get(webDriver.getCurrentUrl());
        WebElement loginLink= webDriver.findElement(By.xpath("//a[@name='Login']"));
        loginLink.click();

        WebElement username= webDriver.findElement(By.xpath("//input[@name='j_username']"));
        WebElement password= webDriver.findElement(By.xpath("//input[@name='j_password']"));
        WebElement login= webDriver.findElement(By.xpath("//button[@name='loginbutton']"));

        username.sendKeys("user");
        password.sendKeys("user");
        login.click();

    }

    @Test
    public void searchBar(){
        webDriver.get("http://pkhw22ds2xds.cloud.wavemakeronline.com/WaveKart/#/Main");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        WebElement searchBar= webDriver.findElement(By.xpath("//input[@name='productSearch']"));

        searchBar.sendKeys("Moto G");

        webDriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        searchBar.sendKeys(Keys.ENTER);
        String attribute=webDriver.findElement(By.name("productSearch")).getAttribute("value");
        String s= searchBar.getText();
        //Assert.assertEquals(attribute,s);

    }

    @Test
    public void allProducts(){

        webDriver.get("http://pkhw22ds2xds.cloud.wavemakeronline.com/WaveKart/#/Main");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.get(webDriver.getCurrentUrl());
        WebElement allProductsLink= webDriver.findElement(By.xpath("//a[@name='ProductsPage']"));
        allProductsLink.click();
        //String attribute=webDriver.findElement(By.name("ProductsPage")).getAttribute("value");
        //Assert.assertTrue(attribute.equals());
    }

    @Test
    public void checkBoxForOutOfStock(){

        webDriver.get("http://pkhw22ds2xds.cloud.wavemakeronline.com/WaveKart/#/Main");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.get(webDriver.getCurrentUrl());
        WebElement allProductsLink= webDriver.findElement(By.xpath("//a[@name='ProductsPage']"));
        allProductsLink.click();
        WebElement checkBox= webDriver.findElement(By.xpath("//span[@class='caption']"));
        checkBox.click();
        //Assert.assertTrue(webDriver.findElement(By.cssSelector("span[class='caption']")).isSelected());
    }

    @Test
    public void checkIfSortedLowToHigh(){
        webDriver.get("http://pkhw22ds2xds.cloud.wavemakeronline.com/WaveKart/#/AllProducts");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        webDriver.get(webDriver.getCurrentUrl());

        WebElement lowToHigh= webDriver.findElement(By.xpath("//span[text()='Price - Low to High']"));
        lowToHigh.click();

        ArrayList<String> obtainedList = new ArrayList<>();
        ArrayList<Integer> prices=new ArrayList<>();
        //WebElement e= webDriver.findElement(By.xpath("//label[@name='price']"));
        List<WebElement> elementList= webDriver.findElements(By.xpath("//div[@name='card_content_AllProducts']//label[@name='Price']"));
        for(WebElement we:elementList){
            String s=we.getText();
            obtainedList.add(s);
        }

        for (int i=0;i<obtainedList.size();i++){
            if(obtainedList.get(i).equals("")){
                continue;
            }
            else {
                prices.add(Integer.parseInt(obtainedList.get(i).substring(2)));
            }
        }
        System.out.println(prices);
        List<Integer> copy=new ArrayList<>(prices);
        Collections.sort(copy);
        if(prices.equals(copy)){
            System.out.println("YES PRODUCTS ARE SORTED LOW TO HIGH ACCORDING TO PRICE");
        }
        else {
            System.out.println("NO PRODUCTS ARE NOT SORTED FROM LOW TO HIGH ACCORDING TO PRICE");
        }

        Assert.assertEquals(copy,prices);

    }

    @Test
    public void sortHighToLow(){
        webDriver.get("http://pkhw22ds2xds.cloud.wavemakeronline.com/WaveKart/#/AllProducts");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        webDriver.get(webDriver.getCurrentUrl());

        WebElement highToLow= webDriver.findElement(By.xpath("//span[text()='Price - High to Low']"));
        highToLow.click();

        ArrayList<String> obtainedList = new ArrayList<>();
        ArrayList<Integer> prices=new ArrayList<>();
        //WebElement e= webDriver.findElement(By.xpath("//label[@name='price']"));
        List<WebElement> elementList= webDriver.findElements(By.xpath("//div[@name='card_content_AllProducts']//label[@name='Price']"));
        for(WebElement we:elementList){
            String s=we.getText();
            obtainedList.add(s);
        }

        for (int i=0;i<obtainedList.size();i++){
            if(obtainedList.get(i).equals("")){
                continue;
            }
            else {
                prices.add(Integer.parseInt(obtainedList.get(i).substring(2)));
            }
        }
        System.out.println(prices);

        List<Integer> copy=new ArrayList<>(prices);
        Collections.sort(copy);
        Collections.reverse(copy);
        if(prices.equals(copy)){
            System.out.println("YES PRODUCTS ARE SORTED FROM HIGH TO LOW ACCORDING TO PRICE");
        }
        else {
            System.out.println("NO PRODUCTS ARE NOT SORTED FROM HIGH TO LOW ACCORDING TO PRICE");
        }
        Assert.assertEquals(copy,prices);
    }

    @Test
    public void addToCart(){
        webDriver.get("http://pkhw22ds2xds.cloud.wavemakeronline.com/WaveKart/#/Products?Id=1");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.get(webDriver.getCurrentUrl());
        WebElement availability= webDriver.findElement(By.xpath("//button[@name='AddToCart']"));
        availability.click();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println(webDriver.getCurrentUrl());

        WebElement username= webDriver.findElement(By.xpath("//input[@name='j_username']"));
        WebElement password= webDriver.findElement(By.xpath("//input[@name='j_password']"));
        WebElement login= webDriver.findElement(By.xpath("//button[@name='loginbutton']"));

        username.sendKeys("user");
        password.sendKeys("user");
        login.click();

        String attribute1=webDriver.getCurrentUrl();

        webDriver.get("http://pkhw22ds2xds.cloud.wavemakeronline.com/WaveKart/#/MyCart");
        webDriver.get(webDriver.getCurrentUrl());
        WebElement myCart= webDriver.findElement(By.xpath("//a[@name='MyCart']"));
        String attribute=webDriver.findElement(By.name("MyCart")).getAttribute("value");
        //Assert.assertEquals(attribute,attribute1);

    }

    @Test
    public void placeOrder(){
        webDriver.get("http://pkhw22ds2xds.cloud.wavemakeronline.com/WaveKart/#/Main");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        webDriver.get(webDriver.getCurrentUrl());
        WebElement loginLink= webDriver.findElement(By.xpath("//a[@name='Login']"));
        loginLink.click();

        WebElement username= webDriver.findElement(By.xpath("//input[@name='j_username']"));
        WebElement password= webDriver.findElement(By.xpath("//input[@name='j_password']"));
        WebElement login= webDriver.findElement(By.xpath("//button[@name='loginbutton']"));

        username.sendKeys("user");
        password.sendKeys("user");
        login.click();

        WebElement myCart= webDriver.findElement(By.xpath("//a[@name='MyCart']"));
        myCart.click();

        WebElement placeOrder= webDriver.findElement(By.xpath("//button[@name='button5']"));
        placeOrder.click();

        System.out.println(webDriver.getCurrentUrl());
        /*webDriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebElement o=webDriver.findElement(By.xpath("//button[@name='nextBtn_wizard_payment']"));
        o.click();*/

    }

    @Test
    public void cancelOrder(){
        webDriver.get("http://pkhw22ds2xds.cloud.wavemakeronline.com/WaveKart/#/Main");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.get(webDriver.getCurrentUrl());
        WebElement loginLink= webDriver.findElement(By.xpath("//a[@name='Login']"));
        loginLink.click();

        WebElement username= webDriver.findElement(By.xpath("//input[@name='j_username']"));
        WebElement password= webDriver.findElement(By.xpath("//input[@name='j_password']"));
        WebElement login= webDriver.findElement(By.xpath("//button[@name='loginbutton']"));

        username.sendKeys("user");
        password.sendKeys("user");
        login.click();

        WebElement myOrders= webDriver.findElement(By.xpath("//a[@name='MyOrders']"));
        myOrders.click();

        webDriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebElement cancelOrder= webDriver.findElement(By.xpath("//button[@name='button3']"));
        cancelOrder.click();

    }

    @Test
    public void logOut(){
        webDriver.get("http://pkhw22ds2xds.cloud.wavemakeronline.com/WaveKart/#/Main");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        webDriver.get(webDriver.getCurrentUrl());
        WebElement loginLink= webDriver.findElement(By.xpath("//a[@name='Login']"));
        loginLink.click();

        WebElement username= webDriver.findElement(By.xpath("//input[@name='j_username']"));
        WebElement password= webDriver.findElement(By.xpath("//input[@name='j_password']"));
        WebElement login= webDriver.findElement(By.xpath("//button[@name='loginbutton']"));

        username.sendKeys("user");
        password.sendKeys("user");
        login.click();

        webDriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebElement logOut= webDriver.findElement(By.xpath("//a[@name='Logout']"));
        logOut.click();

        Assert.assertEquals(webDriver.getCurrentUrl(),"http://pkhw22ds2xds.cloud.wavemakeronline.com/WaveKart/#/Main");
    }

}
