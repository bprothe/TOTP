package AppData;

import dev.samstevens.totp.code.CodeGenerator;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.exceptions.CodeGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.concurrent.TimeUnit;

public class Controller {
    public Text otpCode;
    public String secret = "";
    public int timePeriod = 30;
    public TimeProvider timeProvider = new SystemTimeProvider();
    public CodeGenerator codeGenerator = new DefaultCodeGenerator();
    public long currentBucket;
    public Button btnCopy;
    public Button btnChange;
    public TextField barcode;
    public Button btnCancel;

    public Controller() {

    }

    public void btnCancel() throws InterruptedException, CodeGenerationException {
        barcode.setVisible(false);
        btnChange.setVisible(true);
        btnCopy.setVisible(true);
        btnCancel.setVisible(false);
        start(secret);
    }

    public void btnChange() {
        btnCancel.setVisible(true);
        btnCopy.setVisible(false);
        barcode.setVisible(true);
        barcode.setText("");
    }

    public void btnCopy() {
        StringSelection selection = new StringSelection(otpCode.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

    public void txtBarcode() throws InterruptedException, CodeGenerationException {
        secret = barcode.getText();
        if(!barcode.getText().isEmpty()) {
            barcode.setVisible(false);
            btnChange.setVisible(true);
            btnCopy.setVisible(true);
            btnCancel.setVisible(false);
            start(secret);
        }
    }

    private void start(String secret) throws CodeGenerationException, InterruptedException {
        currentBucket = Math.floorDiv(timeProvider.getTime(), (long) timePeriod);
        //long time = timeProvider.getTime()/30;
        otpCode.setText(codeGenerator.generate(secret, currentBucket));
//        while(time == timeProvider.getTime()/30){
//            TimeUnit.SECONDS.sleep(1);
//        }
//        while(true) {
//            otpCode.setText(codeGenerator.generate(secret, currentBucket));
//            TimeUnit.SECONDS.sleep(30);
//        }
    }


//    public Controller() throws InterruptedException, CodeGenerationException {
//        currentBucket = Math.floorDiv(timeProvider.getTime(), (long) timePeriod);
//        long time = timeProvider.getTime()/30;
//        code.setText(codeGenerator.generate(Model.secret, currentBucket));
//        while(time == timeProvider.getTime()/30){
//            TimeUnit.SECONDS.sleep(1);
//        }
//        while(true) {
//            code.setText(codeGenerator.generate(Model.secret, currentBucket));
//            TimeUnit.SECONDS.sleep(30);
//        }
//    }


//    public Controller() throws CodeGenerationException, InterruptedException {
//        SecretGenerator secretGenerator = new DefaultSecretGenerator();
//        String secret = secretGenerator.generate();
//        secret = "FOK3EPFJS743DO6OLIGNVTFH24OLO5TIBZIE3PLE327R4CDTT63A";
//        QrData.Builder data = new QrData.Builder();
//        data.label("example@example.com");
//        data.secret(secret);
//        data.issuer("AppName");
//        data.algorithm(HashingAlgorithm.SHA1); // More on this below
//        data.digits(6);
//        data.period(30);
//        long currentBucket = Math.floorDiv(timeProvider.getTime(), (long) timePeriod);
//        long time = timeProvider.getTime() / 30;
//        code.setText(codeGenerator.generate(secret, currentBucket));
//
//    }

//        public static void change (ActionEvent actionEvent) throws CodeGenerationException, InterruptedException {
//            long currentBucket = Math.floorDiv(timeProvider.getTime(), (long) timePeriod);
//            code.setText(codeGenerator.generate(secret, currentBucket));
////            SecretGenerator secretGenerator = new DefaultSecretGenerator();
////            int timePeriod = 30;
////            String secret = secretGenerator.generate();
////            secret = "FOK3EPFJS743DO6OLIGNVTFH24OLO5TIBZIE3PLE327R4CDTT63A";
////            QrData.Builder data = new QrData.Builder();
////            data.label("example@example.com");
////            data.secret(secret);
////            data.issuer("AppName");
////            data.algorithm(HashingAlgorithm.SHA1); // More on this below
////            data.digits(6);
////            data.period(30);
////            TimeProvider timeProvider = new SystemTimeProvider();
////            CodeGenerator codeGenerator = new DefaultCodeGenerator();
////            long currentBucket = Math.floorDiv(timeProvider.getTime(), (long) timePeriod);
////        long time = timeProvider.getTime()/30;
//
////        while(time == timeProvider.getTime()/30){
////            TimeUnit.SECONDS.sleep(1);
////        }
////        while(true) {
////            code.setText(codeGenerator.generate(secret, currentBucket));
////            TimeUnit.SECONDS.sleep(30);
////        }
//        }

}

