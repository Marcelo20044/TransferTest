package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountField = $("[data-test-id='amount'] input");
    private SelenideElement cardTransferFrom = $("[data-test-id='from'] input");
    private SelenideElement actionTransferButton = $("[data-test-id='action-transfer']");

    public TransferPage() {
        amountField.shouldBe(visible);
    }


    public DashboardPage transferOperation(String amountToTransfer, DataHelper.CardInfo info) {
        amountField.setValue(amountToTransfer);
        cardTransferFrom.setValue(info.getCardNumber());
        actionTransferButton.click();
        return new DashboardPage();
    }
}
