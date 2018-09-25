class IndexController < ApplicationController

  def index

    @country = Country.all
  end
end
