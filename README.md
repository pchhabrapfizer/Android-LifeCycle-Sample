# Android-LifeCycle


Activity Lifecycle

Activities have a predefined life-cycle and which certain methods are called.

onCreate()

Called then the activity is created. Used to initialize the activity, for example create the user interface.

onResume()

Called if the activity get visible again and the user starts interacting with the activity again. Used to initialize fields, register listeners, bind to services, etc.

onPause()

Called once another activity gets into the foreground. Always called before the activity is not visible anymore. Used to release resources or save application data. For example you unregister listeners, intent receivers, unbind from services or remove system service listeners.

onStop()

Called once the activity is no longer visible. Time or CPU intensive shut-down operations, such as writing information to a database should be down in the onStop() method. This method is guaranteed to be called as of API 11.

![Activity Lifecycle](activity_lifecycle.png?raw=true "Title")




