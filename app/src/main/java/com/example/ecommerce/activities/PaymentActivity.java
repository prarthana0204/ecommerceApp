package com.example.ecommerce.activities;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {

    double amount = 0.0;
    Toolbar toolbar;
    TextView subTotal,discount,shipping,total;
    Button paymentBtn;
    double discountPrice=0;
    double totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        toolbar=findViewById(R.id.payment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        amount=getIntent().getDoubleExtra("amount",0.0);
        subTotal=findViewById(R.id.sub_total);
        discount=findViewById(R.id.textView17);
        shipping=findViewById(R.id.textView18);
        total=findViewById(R.id.total_amt);
        paymentBtn=findViewById(R.id.pay_btn);
        subTotal.setText("₹"+amount);
        discountPrice = (double) ((amount/100)*5);
        discount.setText("₹"+String.valueOf(discountPrice));
        double shippingPrice=150;
        shipping.setText("₹"+String.valueOf(shippingPrice));
        totalAmount= (double) (amount-discountPrice+shippingPrice);
        total.setText("₹"+totalAmount);
        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentMethod();
            }
        });
    }

    private void paymentMethod() {
        Checkout checkout = new Checkout();

        final Activity activity = PaymentActivity.this;

        try {
            JSONObject options = new JSONObject();
            //Set Company Name
            options.put("name", "My E-Commerce App");
            //Ref no
            options.put("description", "Reference No. #123456");
            //Image to be display
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_9A33XWu170gUtm");
            // Currency type
            options.put("currency", "INR");
            //double total = Double.parseDouble(mAmountText.getText().toString());
            //multiply with 100 to get exact amount in rupee
             totalAmount=totalAmount*100;
            //amount
            options.put("amount", totalAmount);
            JSONObject preFill = new JSONObject();
            //email
            preFill.put("email", "prarthamanavi@gmail.com");
            //contact
            preFill.put("contact", "7676466127");

            options.put("prefill", preFill);

            checkout.open(activity, options);
        } catch (Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Cancel", Toast.LENGTH_SHORT).show();

    }
}
