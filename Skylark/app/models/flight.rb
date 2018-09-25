class Flight < ApplicationRecord

  self.table_name = 'flights'

  has_one :depart, class_name: 'Airport', primary_key: 'Departure_Airport_ID', foreign_key: 'id'
  has_one :arrival, class_name: 'Airport', primary_key: 'Arrival_Airport_ID', foreign_key: 'id'
end
