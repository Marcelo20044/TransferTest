package test;

import com.codeborne.selenide.Configuration;
import data.DataHelper;
import lombok.val;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCardsToFirstCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verifyInfo);
        val firstCardInfo = DataHelper.getFirstCardInfo();
        val secondCardInfo = DataHelper.getSecondCardInfo();
        int amount = 1000;
        val expectedBalanceFirstCard = dashboardPage.getFirstCardBalance() + amount;
        val expectedBalanceSecondCard = dashboardPage.getSecondCardBalance() - amount;
        val transferPage = dashboardPage.selectCardToTransfer(firstCardInfo);
        val dashboardPageAfterTransfer = transferPage.transferOperation(String.valueOf(amount), secondCardInfo);
        val actualBalanceFirstCard = dashboardPageAfterTransfer.getFirstCardBalance();
        val actualBalanceSecondCard = dashboardPageAfterTransfer.getSecondCardBalance();
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }


    @Test
    void shouldTransferMoneyBetweenOwnCardsToSecondCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verifyInfo);
        val firstCardInfo = DataHelper.getFirstCardInfo();
        val secondCardInfo = DataHelper.getSecondCardInfo();
        int amount = 1000;
        val expectedBalanceFirstCard = dashboardPage.getFirstCardBalance() - amount;
        val expectedBalanceSecondCard = dashboardPage.getSecondCardBalance() + amount;
        val transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        val dashboardPageAfterTransfer = transferPage.transferOperation(String.valueOf(amount), firstCardInfo);
        val actualBalanceFirstCard = dashboardPageAfterTransfer.getFirstCardBalance();
        val actualBalanceSecondCard = dashboardPageAfterTransfer.getSecondCardBalance();
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

}