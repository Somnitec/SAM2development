package club.nonhuman.sam2android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sumup.merchant.api.SumUpAPI;
import com.sumup.merchant.api.SumUpPayment;

import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private TextView textView_buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnBuy = (Button) findViewById(R.id.button_buy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                int decimals = r.nextInt(100);
                float amount = 1 + ((float) decimals) / 100;

                SumUpPayment payment = SumUpPayment.builder()
                        // mandatory parameters
                        // Please go to https://me.sumup.com/developers to retrieve your Affiliate Key by entering the application ID of your app. (e.g. com.sumup.sdksampleapp)
                        .affiliateKey("96219b93-6ad9-4423-af86-c19e8ab3a3c4")
                        .productAmount(amount)
                        .currency(SumUpPayment.Currency.EUR)
                        .productTitle("Kefir Soda")
                        .receiptEmail("sam@nonhuman.club")
                        .addAdditionalInfo("AccountId", "SAM")
                        // optional: foreign transaction ID, must be unique!
                        .foreignTransactionId(UUID.randomUUID().toString())  // can not exceed 128 chars
                        // optional: skip the success screen
                        .skipSuccessScreen()
                        .build();

                SumUpAPI.openPaymentActivity(MainActivity.this, payment, 1);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2 && data != null) {
            Bundle extra = data.getExtras();
            textView_buy.setText("Message: " + extra.getString(SumUpAPI.Response.MESSAGE));

        }
    }



}
