import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import project.filmbackend.Actor;
import project.filmbackend.Category;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CategoryTest {


    @Test
    public void categoryConstructor(){


        Date lastUpdate=new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            lastUpdate = dateFormatter.parse("2021-11-11");
        } catch (ParseException e) {
            System.out.println("Unparseable using " + dateFormatter);
        }


        Category testCategory=new Category(1,"Action",lastUpdate);

        Assertions.assertEquals(1,testCategory.getCategoryId(),"category id should be 1");
//
        Assertions.assertEquals("2021-11-11",dateFormatter.format(testCategory.getLastUpdate()),"Last update is not correct");
        Assertions.assertEquals("Action",testCategory.getCategoryName(),"category name is incorrect should be action");

//
        //        Assertions.assertEquals("bob",testActor.getFirstName(),"actor id should be 1");
//        Assertions.assertEquals("smith",testActor.getLastName(),"actor id should be 1");



    }


    @Test
    public void categoryIdGetAndSet(){
        Date lastUpdate=new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            lastUpdate = dateFormatter.parse("2021-11-11");
        } catch (ParseException e) {
            System.out.println("Unparseable using " + dateFormatter);
        }

        Category testCategory=new Category(1,"Action",lastUpdate);

        Assertions.assertEquals(1,testCategory.getCategoryId(),"category id should be 1");
        testCategory.setCategoryId(2);
        Assertions.assertEquals(2,testCategory.getCategoryId(),"category id should be 1");
//


    }

    @Test
    public void categoryNameGetAndSet(){
        Date lastUpdate=new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            lastUpdate = dateFormatter.parse("2021-11-11");
        } catch (ParseException e) {
            System.out.println("Unparseable using " + dateFormatter);
        }

        Category testCategory=new Category(1,"Action",lastUpdate);


        Assertions.assertEquals("Action",testCategory.getCategoryName(),"category name should be Action");
        testCategory.setCategoryName("Comedy");
        testCategory.setCategoryId(2);
        Assertions.assertEquals("Comedy",testCategory.getCategoryName(),"category name should be Comedy");
//


    }

    @Test
    public void categoryDateGetAndSet(){
        Date lastUpdate=new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            lastUpdate = dateFormatter.parse("2021-11-11");
        } catch (ParseException e) {
            System.out.println("Unparseable using " + dateFormatter);
        }
//
        Category testCategory=new Category(1,"Action",lastUpdate);

        Assertions.assertEquals("2021-11-11",dateFormatter.format(testCategory.getLastUpdate()),"Last update is not correct");

        try {
            lastUpdate = dateFormatter.parse("2021-11-12");
        } catch (ParseException e) {
            System.out.println("Unparseable using " + dateFormatter);
        }
        testCategory.setLastUpdate(lastUpdate);
        Assertions.assertEquals("2021-11-12",dateFormatter.format(testCategory.getLastUpdate()),"Last update is not correct");



    }
}
