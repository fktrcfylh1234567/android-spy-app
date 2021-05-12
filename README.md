# Sample Android app, that demonstrates different malicious technics. 

```shell script
adb shell dpm set-device-owner com.mosobl.spyapp/.DevAdminReceiver
```

```shell script
adb shell dpm remove-active-admin com.mosobl.spyapp/.DevAdminReceiver
```
