package springrtsru

import org.apache.wicket.Page
import org.apache.wicket.core.request.handler.ListenerInterfaceRequestHandler
import org.apache.wicket.core.request.mapper.MountedMapper
import org.apache.wicket.protocol.http.WebApplication
import org.apache.wicket.request.{IRequestHandler, Url}
import org.apache.wicket.request.component.IRequestablePage
import org.apache.wicket.request.mapper.info.PageComponentInfo
import org.apache.wicket.request.mapper.parameter.PageParametersEncoder
import springrtsru.pages.{WidgetsPacks, AboutPage}

class SpringrtsRuApplication extends WebApplication {
  override def getHomePage: Class[_ <: Page] = classOf[AboutPage]


  override def init(): Unit = {
    getRootRequestMapperAsCompound.add(new MountedMapperWithoutPageComponentInfo("/widgetsPacks", classOf[WidgetsPacks]))
  }

  private class MountedMapperWithoutPageComponentInfo(mountPath: String, pageClass: Class[_ <: IRequestablePage])
    extends MountedMapper(mountPath, pageClass, new PageParametersEncoder()) {
    override def encodePageComponentInfo(url: Url, info: PageComponentInfo): Unit = {}

    override def mapHandler(requestHandler: IRequestHandler): Url =
      if (requestHandler.isInstanceOf[ListenerInterfaceRequestHandler]) {
        null
      } else {
        super.mapHandler(requestHandler)
      }
  }
}
