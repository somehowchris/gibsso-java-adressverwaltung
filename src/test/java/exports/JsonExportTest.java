/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exports;

import adressverwaltung.enums.DotEnvEnum;
import adressverwaltung.enums.SystemPropertyEnum;
import adressverwaltung.errors.CanNotConnectToDatabaseError;
import adressverwaltung.errors.DatabaseSelfHealingError;
import adressverwaltung.exports.ExcelExport;
import adressverwaltung.models.Person;
import adressverwaltung.utils.InOut;
import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import com.github.javafaker.Faker;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chris
 */
public class JsonExportTest {

    ExcelExport export;
    DB db;
    InOut io;
    String path;

    /**
     * Empty Constructor
     */
    public JsonExportTest() {

    }

    /**
     * Setup class
     */
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     * Tear down class
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Setup before test
     * @throws SQLException
     */
    @Before
    public void setUp() throws SQLException {
        int port = 3006;
        try (ServerSocket socket = new ServerSocket(0)) {
            port = socket.getLocalPort();
            socket.close();
        } catch (IOException ex) {
        }

        try {
            db = DB.newEmbeddedDB(port);
            db.start();
            HashMap<String, String> fakeKeys = new HashMap<>();
            fakeKeys.put(DotEnvEnum.DB_USE.get(), "true");
            fakeKeys.put(DotEnvEnum.HOST.get(), "localhost");
            fakeKeys.put(DotEnvEnum.PASSWORD.get(), "root");
            fakeKeys.put(DotEnvEnum.USER.get(), "");
            fakeKeys.put(DotEnvEnum.TABLE_NAME.get(), "test");
            fakeKeys.put(DotEnvEnum.PORT.get(), db.getConfiguration().getPort() + "");
            io = new InOut(fakeKeys);
        } catch (ManagedProcessException | SQLException | CanNotConnectToDatabaseError ex) {
            throw new Error("Could not create database on port 3308");
        } catch (DatabaseSelfHealingError ex) {
            Logger.getLogger(JsonExportTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Faker faker = new Faker();

        for (int i = 0; i < 100; i++) {
            Person p = new Person(faker.name().lastName(), faker.name().firstName(), faker.address().streetAddress(), -1L, faker.phoneNumber().phoneNumber(), faker.phoneNumber().cellPhone(), faker.internet().emailAddress());
            io.savePerson(p);
        }
    }

    /**
     * Tear down after test
     */
    @After
    public void tearDown() {
        try {
            new File(path).delete();
            db.stop();
        } catch (ManagedProcessException ex) {
        }
    }

    /**
     * Testing json exports
     */
    @Test
    public void createJsonExport() {
        path = SystemPropertyEnum.USER_DIR.get() + SystemPropertyEnum.FILE_SEPARATOR.get() + "test.json";
        export = new ExcelExport(io.connection);
        export.setPath(path);
        export.render();
        export.write();
        assertEquals(true, new File(path).exists());
    }
}
