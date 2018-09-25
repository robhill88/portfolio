class HotelController < ApplicationController



  def displayall
  @hotel = Hotel.all
  @country = Country.all
  end


  def search_country

    @hotel = Hotel
  end
end
