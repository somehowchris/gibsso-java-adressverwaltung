/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import adressverwaltung.enums.FileTypeEnum;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import adressverwaltung.utils.FileTypeFilter;
import java.io.File;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author Christof Weickhardt
 */
public class FileTypeFilterTest {

    HashMap<FileTypeFilter, FileTypeEnum> fileTypes;

    public FileTypeFilterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        fileTypes = new HashMap<>();
        for (FileTypeEnum fileType : FileTypeEnum.values()) {
            fileTypes.put(new FileTypeFilter(fileType), fileType);
        }
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void descriptionExists() {
        fileTypes.keySet().forEach((key) -> {
            assertNotNull(key.getDescription());
        });
    }

    @Test
    public void extensionsCorrect() {
        fileTypes.entrySet().forEach((entry) -> {
            assertEquals(entry.getKey().getExtension(), entry.getValue().getExtension());
        });
    }

    @Test
    public void returnsCorrectFileType() {
        fileTypes.entrySet().forEach((entry) -> {
            assertEquals(entry.getKey().getFileType(), entry.getValue());
        });
    }

    @Test
    public void acceptsFileTypes() {
        fileTypes.entrySet().forEach((entry) -> {
            File f = new File("hello" + entry.getKey().getExtension());
            assertEquals(entry.getKey().accept(f), true);
        });
    }

    @Test
    public void rejectsWrongFileTypes() {
        fileTypes.entrySet().forEach((entry) -> {
            File f = new File("None.OfYourBusiness");
            assertEquals(entry.getKey().accept(f), false);
        });
    }

}
