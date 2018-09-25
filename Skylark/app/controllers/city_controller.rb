class CityController < ApplicationController

  def displayall
    @city = City.all
  end
end
