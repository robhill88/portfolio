# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 0) do

  create_table "OrderStatus", id: :integer, force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=latin1" do |t|
    t.string "name", limit: 20, null: false
  end

  create_table "airports", id: :integer, force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=latin1" do |t|
    t.string "name", limit: 50, null: false
    t.integer "City_ID", null: false
    t.index ["City_ID"], name: "City_ID"
  end

  create_table "cities", id: :integer, force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=latin1" do |t|
    t.string "name", limit: 30, null: false
    t.integer "Country_ID", null: false
  end

  create_table "countries", id: :integer, force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=latin1" do |t|
    t.string "name", limit: 30, null: false
  end

  create_table "cruises", primary_key: "Cruise_ID", id: :integer, force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=latin1" do |t|
    t.string "Name", limit: 30, null: false
    t.decimal "Cost", precision: 10, null: false
    t.integer "Duration", null: false
    t.integer "Cruise_Locations_ID", null: false
    t.decimal "Cost_First", precision: 10, null: false
    t.decimal "Cost_Business", precision: 10, null: false
    t.decimal "Cost_Economy", precision: 10, null: false
    t.integer "Rooms_Available_First", null: false
    t.integer "Rooms_Available_Economy", null: false
    t.string "Picture_URL", limit: 30, null: false
  end

  create_table "customerOrder", id: :integer, force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=latin1" do |t|
    t.integer "customer_id", null: false
    t.integer "customerItem", null: false
    t.integer "orderStatus", null: false
    t.integer "totalCost", null: false
  end

  create_table "customers", id: :integer, force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=latin1" do |t|
    t.string "first_name", limit: 30, null: false
    t.string "surname", limit: 30, null: false
    t.string "username", limit: 30, null: false
    t.string "password", limit: 256, null: false
  end

  create_table "flights", id: :integer, force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=latin1" do |t|
    t.integer "Departure_Airport_ID", null: false
    t.integer "Arrival_Airport_ID", null: false
    t.date "Departure_Date", null: false
    t.time "Duration", null: false
    t.decimal "Cost_First", precision: 10, null: false
    t.decimal "Cost_Business", precision: 10, null: false
    t.decimal "Cost_Economy", precision: 10, null: false
    t.integer "Seats_Available_First", null: false
    t.integer "Seats_Available_Business", null: false
    t.integer "Seats_Available_Economy", null: false
    t.time "Departure_Time", null: false
    t.time "Arrival_Time", null: false
    t.index ["Arrival_Airport_ID"], name: "Arrival_Airport_ID"
    t.index ["Departure_Airport_ID"], name: "Departure_Airport_ID"
  end

  create_table "hotels", primary_key: "Hotel_ID", id: :integer, force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=latin1" do |t|
    t.string "Name", limit: 30, null: false
    t.integer "City_ID", null: false
    t.decimal "Cost_Per_Night", precision: 10, null: false
    t.integer "Rooms_Available", null: false
    t.boolean "All_Inclusive", null: false
    t.boolean "Swimming_Pool", null: false
    t.string "Picture_URL", limit: 30, null: false
    t.index ["City_ID"], name: "City_ID"
  end

  create_table "pre_packages", id: :integer, force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=latin1" do |t|
    t.integer "Cost", null: false
    t.integer "Flight_To_ID", null: false
    t.integer "Flight_Home_ID", null: false
    t.integer "Hotel_ID", null: false
    t.integer "Duration", null: false
    t.integer "adults", null: false
    t.integer "children", null: false
    t.string "image", limit: 20, null: false
    t.text "descriptioin", limit: 4294967295, null: false
  end

  create_table "products", id: :integer, force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=latin1" do |t|
    t.integer "product_id", null: false
  end

  create_table "qHighScore_Large", primary_key: "ID", id: :integer, default: nil, force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=latin1" do |t|
    t.string "Name", limit: 3, null: false
    t.float "Score", limit: 53, null: false
  end

  create_table "qHighScore_Medium", id: false, force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=latin1" do |t|
    t.integer "ID", null: false
    t.string "Name", limit: 3, null: false
    t.integer "Score", null: false
  end

  create_table "qHighScore_Small", id: false, force: :cascade, options: "ENGINE=InnoDB DEFAULT CHARSET=latin1" do |t|
    t.integer "ID", null: false
    t.string "Name", limit: 3, null: false
    t.integer "Score", null: false
  end

end
