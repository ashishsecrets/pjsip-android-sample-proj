package com.example.freeswitchandroid.rest.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


    public class UserDatum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("business_numbers")
        @Expose
        private List<BusinessNumber> businessNumbers;

        /**
         * No args constructor for use in serialization
         *
         */
        public UserDatum() {
        }

        /**
         *
         * @param businessNumbers
         * @param id
         */
        public UserDatum(Integer id, List<BusinessNumber> businessNumbers) {
            super();
            this.id = id;
            this.businessNumbers = businessNumbers;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public List<BusinessNumber> getBusinessNumbers() {
            return businessNumbers;
        }

        public void setBusinessNumbers(List<BusinessNumber> businessNumbers) {
            this.businessNumbers = businessNumbers;
        }

    }

