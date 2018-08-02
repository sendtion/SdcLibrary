package com.fb.sdclibrary.utils;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * 蓝牙的工具类
 * 《蓝牙功能需要的相对应权限》
 * <uses-permission android:name="android.permission.BLUETOOTH" />
 * <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
 * <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />《敏感》
 * <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />《敏感》
 * <uses-feature
 * android:name="android.hardware.bluetooth_le"
 * android:required="true" />
 */

public class BlueToothUtil {
    /**
     * 通过系统的方式启动蓝牙
     */
    public static void startForSystem(Activity activity) {
        activity.startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 1);
    }

    /**
     * 静默启动，需要两个的两个敏感权限需要动态的申请（23以上）
     */
    public static BluetoothAdapter startForSilent() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothAdapter.enable();
        return bluetoothAdapter;
    }

    /**
     * 关闭蓝牙
     */
    public static void stopForDefault(BluetoothAdapter bluetoothAdapter) {
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
        } else {
            //处理异常（蓝牙未开启）
        }
    }

    /**
     * 主动搜索
     */
    public static void searchForInitiative(BluetoothAdapter bluetoothAdapter) {
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            // 寻找蓝牙设备，android会将查找到的设备以广播形式发出去
            bluetoothAdapter.startDiscovery();
        } else {
            //处理异常（蓝牙未开启）
            BluetoothAdapter bluetoothAdapter1 = startForSilent();
            if (bluetoothAdapter1 != null) {
                bluetoothAdapter1.startDiscovery();
            }
        }
    }

    /**
     * 被动搜索
     */
    public static void searchForPassive(Activity activity, BluetoothAdapter bluetoothAdapter) {
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            if (bluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
                Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                // 设置被发现时间，最大值是3600秒,0表示设备总是可以被发现的(小于0或者大于3600则会被自动设置为120秒)
                discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 120);
                activity.startActivity(discoverableIntent);
            }
        } else {
            //处理异常（蓝牙未开启）
        }
    }

    /**
     * 显示已配对的设备列表()
     */
    public static List<Map<String, String>> showBoundDevices(BluetoothAdapter bluetoothAdapter) {
        List<Map<String, String>> mBoundDevicesList = new ArrayList<>();
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            Set<BluetoothDevice> boundDeviceSet = bluetoothAdapter.getBondedDevices();
            for (BluetoothDevice boundDevices : boundDeviceSet) {
                Map<String, String> mBoundDevicesMap = new HashMap<>();
                mBoundDevicesMap.put("name", boundDevices.getName());
                mBoundDevicesMap.put("address", boundDevices.getAddress());
                mBoundDevicesList.add(mBoundDevicesMap);
            }
        } else {
            //处理异常（蓝牙未开启）
        }
        return mBoundDevicesList;
    }

    /**
     * 蓝牙配对
     */
    public static void matchesEquipment(BluetoothDevice device) {
        if (device.getBondState() == BluetoothDevice.BOND_BONDED) {//是否已配对
            try {
                connect(device);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Method boned = device.getClass().getMethod("createBond");
                boolean isOk = (boolean) boned.invoke(device);
                if (isOk) {
                    connect(device);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void connect(BluetoothDevice device) throws IOException {
        // 固定的UUID
        final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
        UUID uuid = UUID.fromString(SPP_UUID);
        BluetoothSocket socket = device.createRfcommSocketToServiceRecord(uuid);
        socket.connect();
    }
}
