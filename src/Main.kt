import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import javax.xml.parsers.SAXParserFactory

fun main(args: Array<String>) {
    val file = File("SAXTest.xml")

    /*
    val out = file.run(::FileWriter).run(::BufferedWriter).run(::PrintWriter)
    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
    out.println("<item>orange</item>")
    out.close()
     */

    val parser = SAXParserFactory.newInstance().newSAXParser() ?: return
    parser.parse(file, Handler)
}

object Handler : DefaultHandler() {
    override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
        println("URI=$uri, localName=$localName, qName=$qName")
        if (attributes != null) {
            for (i in 0..attributes.length.minus(1)) {
                println("Type=${attributes.getType(i)}, qName=${attributes.getQName(i)}, URI=${attributes.getURI(i)}, Value=${attributes.getValue(i)}")
            }
        }
    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        println("Item=${ch?.let { String(it, start, length) }}")
    }

    override fun endDocument() {
        println("SAXTest has read!")
    }
}