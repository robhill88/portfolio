Rails.application.routes.draw do
  get 'customer_order/create'

  get 'index/index'

  get 'cruise/displayall'

  get 'city/displayall'

  get 'flight/displayall'

  get 'hotel/displayall'

  get 'packageholiday/displayall'

  get 'basket/addToBasket' => 'basket#addToBasket'

  get 'basket/' => 'basket#showBasket'

  get 'basket/checkout'

  get 'basket/total' => 'basket#total'

  get 'basket/payment'

  get 'basket/finished'

  get 'customer_order/display_order'

  get 'basket/complete' => 'basket#complete'

  get 'basket/remove' => 'basket#remove'

  get 'customer_order/finish' => 'basket#complete'
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html

  root 'index#index'
end
