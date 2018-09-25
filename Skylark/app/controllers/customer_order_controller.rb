class CustomerOrderController < ApplicationController


  def delete_cookie

    cookies.delete (:cart)

    if cookies.has_key?(:order)

      @order2 = JSON.parse(cookies{:order})
    end
  end
end
