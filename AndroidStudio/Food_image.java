package com.example.childmeal;
//package app.ij.mlwithtensorflowlite;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.childmeal.ml.Model;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Food_image extends AppCompatActivity {
    TextView result, wrong_food;
    ImageView imageView;
    Button picture, btn_record, btn_food_toDB;
    int imageSize = 224;
    String result_food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_image);

        result = findViewById(R.id.result);
        //confidence = findViewById(R.id.confidence);
        imageView = findViewById(R.id.imageView);
        picture = findViewById(R.id.button);
        wrong_food = findViewById(R.id.wrong_food);
        btn_record = findViewById(R.id.btn_record_food);
        btn_food_toDB = findViewById(R.id.btn_food_toDB);

        picture.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                // Launch camera if we have permission
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 1);
                } else {
                    //Request camera permission if we don't have it.
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });

        btn_food_toDB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AWS_RDS.add_food(result_food);
                startActivity(new Intent(Food_image.this, MainActivity.class));
            }
        });

        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Food_image.this, Food_Register.class));
            }
        });
    }
    public void getResultFood(String a){
        result_food = a;
    }

    public void classifyImage(Bitmap image){
        try {
            Model model = Model.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4*imageSize*imageSize*3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int [] intValues = new int[imageSize*imageSize];
            image.getPixels(intValues,0, image.getWidth(),0,0,image.getWidth(),image.getHeight());
            int pixel = 0;
            for(int i=0; i< imageSize; i++){
                for(int j=0; j<imageSize; j++){
                    int val = intValues[pixel++]; //RGB
                    byteBuffer.putFloat(((val>>16)&0xFF) *(1.f/255.f));
                    byteBuffer.putFloat(((val>>8)&0xFF) *(1.f/255.f));
                    byteBuffer.putFloat((val &0xFF) *(1.f/255.f));
                }
            }

            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidences = outputFeature0.getFloatArray();
            int maxPos = 0;
            float maxConfidence = 0;
            for (int i=0; i<confidences.length; i++){
                if(confidences[i]>maxConfidence){
                    maxConfidence = confidences[i];
                    maxPos = i;
                }
            }

            String[] classes = {"짬뽕", "돈까스", "짜장면", "우동", "토마토 스파게티","비빔국수","까르보나라",
                    "카레라이스","갈비탕","잔치국수","미역국","회덮밥","김치만두","고기만두","오징어덮밥","불고기덮밥","시금치무침","제육볶음",
                    "김밥","떡볶이","냉면","칼국수","된장찌개","배추김치","김치찌개","깍두기","쌀국수"};
            result.setText(classes[maxPos]);

            getResultFood(classes[maxPos]);
            result.setVisibility(View.VISIBLE);
            btn_record.setVisibility(View.VISIBLE);
            wrong_food.setVisibility(View.VISIBLE);
            btn_food_toDB.setVisibility(View.VISIBLE);

            String s = "";

            for(int i=0; i< classes.length;i++){
                s+= String.format("%s: %.1f%%\n",classes[i],confidences[i]*100);
            }
            //confidence.setText(s);
            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }
    }
    // Intent data -> 이미지 가져옴
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            int dimension = Math.min(image.getWidth(), image.getHeight());
            image = ThumbnailUtils.extractThumbnail(image,dimension, dimension);
            imageView.setImageBitmap(image);

            // image resize
            image = Bitmap.createScaledBitmap(image,imageSize,imageSize,false);
            classifyImage(image);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
