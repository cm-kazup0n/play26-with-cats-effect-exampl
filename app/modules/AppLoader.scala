package modules

import play.api._

class AppLoader extends ApplicationLoader {

  private var components: MyComponents = _

  def load(context: ApplicationLoader.Context): Application = {
    components = new MyComponents(context)
    components.application
  }
}
