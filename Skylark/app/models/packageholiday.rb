class Packageholiday < ApplicationRecord

  self.table_name = 'pre_packages'

  has_one :hotel, class_name: 'Hotel', primary_key: 'Hotel_ID', foreign_key: 'Hotel_ID'
  has_one :flight_to, class_name:'Flight', primary_key: 'Flight_To_ID', foreign_key: 'id'
  has_one :flight_home, class_name: 'Flight', primary_key: 'Flight_Home_ID', foreign_key: 'id'

end
