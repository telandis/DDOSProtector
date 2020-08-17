package ddosprotector

import org.scalatra._
import org.slf4j.LoggerFactory

class DDOSServlet extends ScalatraServlet {

  val logger =  LoggerFactory.getLogger(getClass)

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

  var ddosRecorder = new DDOSRecorder()

  //Get request for clients to hit
  get("/hit") {
    ddosRecorder.hit(request.getRemoteAddr())
    logger.info(request.getRemoteAddr())
    "Request hit received"
  }
}
