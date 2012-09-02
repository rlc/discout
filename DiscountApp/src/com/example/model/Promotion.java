package com.example.model;

public class Promotion {
    //private variables
	int _idPromo;
	int _idMerchant;
	int _idCard;
	String _merchantDesc;
	String _cardDesc;
	String _name;
	String _nameDesc;
	String _promoDesc;
	String _startDate;
	String _endDate;
	String _location;
	String _weekdays;
	String _image;
 
    // Empty constructor
    public Promotion()				{}
    public Promotion(int idPromo, int idMerchant, int idCard, String name,
    		String nameDesc, String promoDesc, String startDate, String endDate,
    		String loc, String days, String image)	
    {
    	this._idPromo		= idPromo;
    	this._idMerchant	= idMerchant;
    	this._idCard		= idCard;
    	this._name 			= name;
    	this._nameDesc		= nameDesc;
    	this._promoDesc		= promoDesc;
    	this._startDate		= startDate;
    	this._endDate		= endDate;
    	this._location		= loc;
    	this._weekdays		= days;    	
    	this._image			= image;
    }
    
    public int getIdPromo()			{return this._idPromo;}
    public int getIdMerchant()		{return this._idMerchant;}
    public int getIdCard()			{return this._idCard;}
    public String getMerchantDesc()	{return this._merchantDesc;}
    public String getCardDesc()		{return this._cardDesc;}        
    public String getName()			{return this._name;}
    public String getNameDesc()		{return this._nameDesc;}
    public String getPromoDesc()	{return this._promoDesc;}
    public String getStartDate()	{return this._startDate;}
    public String getEndDate()		{return this._endDate;}
    public String getLocation()		{return this._location;}
    public String getWeekdays()		{return this._weekdays;}
    public String getImage()		{return this._image;}

    public void setIdPromo(int id)				{this._idPromo 		= id;}
    public void setIdMerchant(int id)			{this._idMerchant 	= id;}
    public void setIdCard(int id)				{this._idCard	 	= id;}
    public void setMerchantDesc(String name)	{this._merchantDesc	= name;}
    public void setCardDesc(String name)		{this._cardDesc		= name;}
    public void setName(String name)			{this._name			= name;}
    public void setNameDesc(String desc)		{this._nameDesc		= desc;}
    public void setPromoDesc(String desc)		{this._promoDesc	= desc;}
    public void setStartDate(String date)		{this._startDate	= date;}
    public void setEndDate(String date)			{this._endDate		= date;}
    public void setLocation(String loc)			{this._location		= loc;}
    public void setWeekdays(String days)		{this._weekdays		= days;}
    public void setImage(String image)			{this._image		= image;}
 }
