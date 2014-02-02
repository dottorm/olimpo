// @SOURCE:/home/marco/workspace/olimpo/conf/routes
// @HASH:33c90d3277218d7ed224d0d137d0f2e70a9abc98
// @DATE:Sun Feb 02 16:42:32 CET 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:10
package controllers {

// @LINE:10
class ReverseAssets {
    

// @LINE:10
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          
}
                  

// @LINE:7
// @LINE:6
package com.olimpo.controllers {

// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:7
def provaJson(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "prova")
}
                                                
    
}
                          
}
                  


// @LINE:10
package controllers.javascript {

// @LINE:10
class ReverseAssets {
    

// @LINE:10
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              
}
        

// @LINE:7
// @LINE:6
package com.olimpo.controllers.javascript {

// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.olimpo.controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:7
def provaJson : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.olimpo.controllers.Application.provaJson",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "prova"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:10
package controllers.ref {


// @LINE:10
class ReverseAssets {
    

// @LINE:10
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          
}
        

// @LINE:7
// @LINE:6
package com.olimpo.controllers.ref {


// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   com.olimpo.controllers.Application.index(), HandlerDef(this, "com.olimpo.controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

// @LINE:7
def provaJson(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   com.olimpo.controllers.Application.provaJson(), HandlerDef(this, "com.olimpo.controllers.Application", "provaJson", Seq(), "GET", """""", _prefix + """prova""")
)
                      
    
}
                          
}
        
    