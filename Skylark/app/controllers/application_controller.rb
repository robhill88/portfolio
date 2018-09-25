class ApplicationController < ActionController::Base
  protect_from_forgery with: :exception

  #$server_route = "/"
  $server_route = "/ip/skylark/"

end
