package noraui.data.csv;

import static noraui.utils.Constants.DATA_IN;
import static noraui.utils.Constants.DATA_OUT;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Test;

import com.opencsv.CSVReader;

import noraui.exception.TechnicalException;
import noraui.utils.Messages;

public class CsvDataProviderUT {

    @Test
    public void testConstructorIsPublic() throws Exception {
        Constructor<CsvDataProvider> constructor = CsvDataProvider.class.getDeclaredConstructor();
        Assert.assertTrue(Modifier.isPublic(constructor.getModifiers()));
        constructor.setAccessible(true);
    }

    @Test
    public void testWriteXxxxxResult() throws TechnicalException, IOException {
        CsvDataProvider csvDataProvider = new CsvDataProvider();
        csvDataProvider.setDataInPath("src/test/resources" + DATA_IN);
        csvDataProvider.setDataOutPath("src/test/resources" + DATA_OUT);
        csvDataProvider.prepare("hello");
        Assert.assertTrue(true);

        Assert.assertEquals(9, csvDataProvider.getNbLines());

        csvDataProvider.writeFailedResult(1, "UT Failed Message");
        CSVReader reader = new CSVReader(new FileReader(new File("src/test/resources" + DATA_OUT + "hello.csv")), ';');
        Assert.assertEquals("UT Failed Message", reader.readAll().get(1)[7]);
        reader.close();

        reader = new CSVReader(new FileReader(new File("src/test/resources" + DATA_OUT + "hello.csv")), ';');
        csvDataProvider.writeSuccessResult(2);
        Assert.assertEquals(Messages.getMessage(Messages.SUCCESS_MESSAGE), reader.readAll().get(2)[7]);
        reader.close();

        reader = new CSVReader(new FileReader(new File("src/test/resources" + DATA_OUT + "hello.csv")), ';');
        csvDataProvider.writeWarningResult(3, "UT Warning Message");
        Assert.assertEquals("UT Warning Message", reader.readAll().get(3)[7]);
        reader.close();

        reader = new CSVReader(new FileReader(new File("src/test/resources" + DATA_OUT + "hello.csv")), ';');
        csvDataProvider.writeDataResult("title", 4, "UT title");
        Assert.assertEquals("UT title", reader.readAll().get(4)[6]);
        reader.close();
    }
}
