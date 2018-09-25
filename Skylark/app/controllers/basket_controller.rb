class BasketController < ApplicationController

 def addToBasket

   array = nil
   if cookies[:cart].nil?

     array = Array.new
   else

     array = JSON.parse(cookies[:cart])
   end

   array.push({:id => params[:id]})
   cookies[:cart] = {:value => JSON.generate(array), :expires => 1.hour.from_now}
   @basket = array

   total
 end



  def remove

    if cookies.has_key?(:cart)
    product = JSON.parse(cookies[:cart])

    product.each do | item |

          if item['id'] == params[:id]
            product.delete(item)
          end

    end

    cookies[:cart] = {:value => JSON.generate(product), :expires => 1.hour.from_now}
    puts cookies[:cart]
      end
  end





  def total

    cost = 0

    array = Array.new
    if cookies.has_key?(:cart)

    holiday = JSON.parse(cookies[:cart])
    puts holiday
    holiday.each do |item|
      puts item['id']
      array.push(item['id'].to_i)
    end

    package = Packageholiday.find(array)

     package.each do |package|

          cost += package.Cost
     end

    puts cost
    end
    render inline: "£" + cost.to_s

  end




  def checkout

    if cookies.has_key?(:cart)
    array = Array.new
    product = JSON.parse(cookies[:cart])
    puts product
    product.each do |item|
      array.push(item['id'].to_i)
    end

    @product = Packageholiday.find(array)
    end
  end




  def complete

    if cookies.has_key?(:cart)
    product_array = JSON.parse(cookies[:cart])
    order_array =
    product_array.each do | item |

      product = Packageholiday.find(item['id'].to_i)
      cost = product.Cost
      order = CustomerOrder.new(:id => nil, :customer_id => '666'.to_i, :customerItem => item['id'].to_i, :orderStatus => '2'.to_i, :totalCost => cost.to_i).save

          end
    end

    cookies.delete (:cart)
  end


end
