import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import project.filmbackend.Category;
import project.filmbackend.Language;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LanguageTest {



    @Test
    public void LanguageConstructor(){


        Date lastUpdate=new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            lastUpdate = dateFormatter.parse("2021-11-11");
        } catch (ParseException e) {
            System.out.println("Unparseable using " + dateFormatter);
        }


        Language testLanguage=new Language(1,"English",lastUpdate);

        Assertions.assertEquals(1,testLanguage.getLanguageId(),"Language id should be 1");
//
        Assertions.assertEquals("2021-11-11",dateFormatter.format(testLanguage.getLastUpdate()),"Last update is not correct");
        Assertions.assertEquals("English",testLanguage.getName(),"language name is incorrect should be English");



    }


    @Test
    public void languageIdGetAndSet(){

        Date lastUpdate=new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            lastUpdate = dateFormatter.parse("2021-11-11");
        } catch (ParseException e) {
            System.out.println("Unparseable using " + dateFormatter);
        }


        Language testLanguage=new Language(1,"English",lastUpdate);

        Assertions.assertEquals(1,testLanguage.getLanguageId(),"Language id should be 1");
//
       testLanguage.setLanguageId(2);
        Assertions.assertEquals(2,testLanguage.getLanguageId(),"Language id should be 2");


    }

    @Test
    public void languageNameGetAndSet(){
        Date lastUpdate=new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            lastUpdate = dateFormatter.parse("2021-11-11");
        } catch (ParseException e) {
            System.out.println("Unparseable using " + dateFormatter);
        }


        Language testLanguage=new Language(1,"English",lastUpdate);

        Assertions.assertEquals("English",testLanguage.getName(),"language name is incorrect should be English");

        testLanguage.setName("French");

        Assertions.assertEquals("French",testLanguage.getName(),"language name is incorrect should be French");


    }

    @Test
    public void languageDateGetAndSet(){
        Date lastUpdate=new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            lastUpdate = dateFormatter.parse("2021-11-11");
        } catch (ParseException e) {
            System.out.println("Unparseable using " + dateFormatter);
        }


        Language testLanguage=new Language(1,"English",lastUpdate);

//
        Assertions.assertEquals("2021-11-11",dateFormatter.format(testLanguage.getLastUpdate()),"Last update is not correct");


        try {
            lastUpdate = dateFormatter.parse("2021-11-12");
        } catch (ParseException e) {
            System.out.println("Unparseable using " + dateFormatter);
        }
        testLanguage.setLastUpdate(lastUpdate);
        Assertions.assertEquals("2021-11-12",dateFormatter.format(testLanguage.getLastUpdate()),"Last update is not correct");


    }
}
