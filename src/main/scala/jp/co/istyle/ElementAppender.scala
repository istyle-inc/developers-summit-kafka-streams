package jp.co.istyle

import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization.write

class ElementAppender(messageBody: String) {

  implicit val formats = DefaultFormats

  def append():String = {
    val js = parse(messageBody)
    val v = js.asInstanceOf[JObject]
    write(js.replace(List("Interests"), JString(new String((v \ "Interests").values.toString + ",Developers Summit"))))
  }
}
