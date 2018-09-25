class PackageholidayController < ApplicationController

  def displayall
    @package = Packageholiday.all
  end
end
