package com.amqtech.permissions.helper.ui.activity;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amqtech.permissions.helper.R;
import com.amqtech.permissions.helper.objects.Permission;
import com.amqtech.permissions.helper.objects.PermissionsFlow;
import com.amqtech.permissions.helper.ui.adapter.PermissionsFlowAdapter;

public class PermissionsFlowActivity extends AppCompatActivity {

    private Permission[] permissions;
    private RecyclerView recyclerView;
    private PermissionsFlowAdapter permissionsFlowAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissions = (Permission[]) getIntent().getExtras().getSerializable(PermissionsFlow.PERMISSIONS);
        if(permissions == null || permissions.length == 0) throw new RuntimeException("No permissions!");
        setContentView(R.layout.activity_permissions_flow);
        recyclerView = (RecyclerView) findViewById(R.id.perms_rv);
        recyclerView.setHasFixedSize(true);
        permissionsFlowAdapter = new PermissionsFlowAdapter(permissions);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(permissionsFlowAdapter);

        Button cancel = (Button) findViewById(R.id.cancel_btn);
        Button next = (Button) findViewById(R.id.continue_btn);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getBaseContext(), "Hi", Toast.LENGTH_SHORT).show();

                for (Permission perm : permissions) {
                    ActivityCompat.requestPermissions(PermissionsFlowActivity.this,
                            new String[]{perm.getPermissionName()}, perm.getPermissions().getRequestCode());
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

    }


}
