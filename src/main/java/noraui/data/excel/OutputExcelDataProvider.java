package noraui.data.excel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import noraui.exception.TechnicalException;
import noraui.exception.data.EmptyDataFileContentException;
import noraui.exception.data.WrongDataFileFormatException;
import noraui.utils.Messages;

public class OutputExcelDataProvider extends ExcelDataProvider {

    /**
     * Specific logger
     */
    private static final Logger logger = LoggerFactory.getLogger(OutputExcelDataProvider.class);

    private static final String EXCEL_OUTPUT_DATA_PROVIDER_USED = "EXCEL_OUTPUT_DATA_PROVIDER_USED";

    public OutputExcelDataProvider() {
        super();
        logger.info(Messages.getMessage(EXCEL_OUTPUT_DATA_PROVIDER_USED));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void prepare(String scenario) throws TechnicalException {
        scenarioName = scenario;
        try {
            openOutputData();
            initColumns();
        } catch (EmptyDataFileContentException | WrongDataFileFormatException e) {
            logger.error(Messages.getMessage(TechnicalException.TECHNICAL_ERROR_MESSAGE_DATA_IOEXCEPTION), e);
            System.exit(-1);
        }
    }
}
