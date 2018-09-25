class Hotel < ApplicationRecord

  self.table_name = 'hotels'

  has_one :city, class_name: 'City', primary_key: 'City_ID', foreign_key: 'id'
end
