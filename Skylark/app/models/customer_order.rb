class CustomerOrder < ApplicationRecord

  self.table_name = 'customerOrder'

  has_one :customer,  class_name: 'Customer', primary_key: 'customer_id', foreign_key: 'id'
  has_one :item, class_name: 'Packageholiday', primary_key: 'customerItem', foreign_key: 'id'
  has_one :status, class_name: 'OrderStatus', primary_key: 'orderStatus', foreign_key: 'id'
end
