class FlightController < ApplicationController
  def displayall

    @flight = Flight.all
  end
end
