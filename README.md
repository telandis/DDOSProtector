# DDOSProtector #

## Build & Run ##

```sh
$ cd ddosprotector
$ sbt
> jetty:start
> browse
```

If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.


This app can receive get requests on /hit. When the same ip address hits the request too many times within a certain
time span, it will get recorded as an offending IP address to a file. A Linked HashMap is used in this implementation
as sort of an LRU cache, with a task runninng to clear the cache every set amount of time has passed.