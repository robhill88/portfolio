class City < ApplicationRecord
  self.table_name = 'cities'

  has_one :country, class_name: 'Country', primary_key: 'Country_ID', foreign_key: 'id'
end
