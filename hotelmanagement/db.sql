

#创建视图保存每种房型的数量
create view v_roomType_number(typeName,typeNumber)
	as select typeName,count(*) from hotelmanagement.roomtype,hotelmanagement.room 
		where roomtype.typeId=room.roomType group by roomType;
update hotelmanagement.room set state=0 where roomId='0201';
select * from v_roomType_number;
#创建视图保存预定与在住信息
#订单状态  保存每种房型的订单数
create view v_orderState(arrive,depart,roomType,roomNumber)
as select book.arrive,book.depart,roomType,roomNumber
		from hotelmanagement.book
	union all
	select checkin.arrive,checkin.depart,roomtype.typeName,checkin.roomId=checkin.roomId
		from hotelmanagement.checkin,hotelmanagement.roomtype,hotelmanagement.room
			where checkin.roomId=room.roomId and room.roomType=roomtype.typeId;
select * from v_orderState; 

select v_roomType_number.typeName,v_roomType_number.typeNumber-ifnull(sum(v_orderstate.roomNumber),0)    
	from v_roomType_number,v_orderstate 
		where v_roomType_number.typeName='单人间' and v_orderstate.roomType='单人间'
        and ('2019-07-15' between v_orderState.arrive and v_orderState.depart);
        
        
select sum(v_orderstate.roomNumber) 
	from v_orderstate 
		where v_orderstate.roomType='单人间'
        and ('2019-07-05' between v_orderState.arrive and v_orderState.depart);
#通过两个视图计算每个日期的剩余房间
select typeName,v_roomType_number.typeNumber-sum(v_orderState.roomNumber) as remain
	from v_roomType_number,v_orderState
		where v_roomType_number.typeName=v_orderState.roomType 
        and ('2019-07-06' between v_orderState.arrive and v_orderState.depart) 
			group by roomType;
#计算指定日期的房间使用数量 
 select *
	from v_orderState
		where '2019-07-04' between date(v_orderState.arrive) and date(v_orderState.depart);
 select *
	from hotelmanagement.book
		where (select DATEDIFF(day,'2017-07-04',date(book.arrive))>=0) 
        and (select DATEDIFF(day,date(book.depart),'2017-07-04')>=1);        
 #v_orderState.roomType='单人间' and     sum(v_orderState.roomNumber)
select DATEDIFF('2019-07-04',date(book.arrive)) from hotelmanagement.book; 
    
#计算每种房型的数量
select typeName,count(*) from hotelmanagement.roomtype,hotelmanagement.room 
		where roomtype.typeId=room.roomType group by roomType;
#获取每种房型的名称
select typeName from hotelmanagement.roomtype where typeId in 
	(select roomType from hotelmanagement.room group by roomType);
select roomType.typeName from hotelmanagement.roomtype,hotelmanagement.room 
		where roomtype.typeId=room.roomType group by roomType;

#通过类型查出房间编号
#然后找到为空的房间列表
select * from hotelmanagement.room where 
	roomType = (select typeId from hotelmanagement.roomtype where typeName='单人间')
    and state = 0 limit 2;
    
select * from(
    select typeName,v_roomType_number.typeNumber-sum(v_orderState.roomNumber) as remain
	        from v_roomType_number,v_orderState
                where v_roomType_number.typeName=v_orderState.roomType
                and (2019-07-06 between v_orderState.arrive and v_orderState.depart)
                    group by roomType) where typeName='单人间';
                    
select roomId from room;
## 先向预定表中插入一条记录保证测试过程可以进行
insert into hotelmanagement.book(bookId,roomType,roomNumber,arrive,depart,paystate,response,phone) 
		values('00001','单人间',2,'2019-07-03','2019-07-06',888,'预定成功','11111111110');
insert into hotelmanagement.book(bookId,roomType,roomNumber,arrive,depart,paystate,response,phone) 
		values('00003','单人间',1,'2019-07-03','2019-07-05',888,'预定成功','11111111110');
insert into hotelmanagement.book(bookId,roomType,roomNumber,arrive,depart,paystate,response,phone) 
		values('00004','单人间',1,'2019-07-06','2019-07-07',128,'预定成功','11111111110');
insert into hotelmanagement.book(bookId,roomType,roomNumber,arrive,depart,paystate,response,phone) 
		values('00005','单人间',1,'2019-07-04','2019-07-05',128,'预定成功','11111111110');
insert into hotelmanagement.book(bookId,roomType,roomNumber,arrive,depart,paystate,response,phone) 
		values('00006','单人间',1,'2019-07-03','2019-07-04',128,'预定成功','11111111110');
delete from hotelmanagement.book where bookId='00006';
## 先向在住记录中插入一条记录保证测试过程可以进行
insert into hotelmanagement.checkin(bookId,arrive,depart,money,roomId,phone,waiter) 
		values('00002','2019-07-02','2019-07-05',444,'0201','11111111110','2222222220');
delete from hotelmanagement.checkin where bookId='00002';
insert into hotelmanagement.history(bookId,arrive,depart,money,state,roomId,phone,waiter) 
		values('00002','2019-07-02','2019-07-05',444,'正常入住','0201','11111111110','2222222220');
drop table hotelmanagement.roomtype;

#获取全部房型
select roomType.typeName from hotelmanagement.roomtype,hotelmanagement.room 
		where roomtype.typeId=room.roomType group by roomType;
#订房过程
#数据添加
#插入管理员记录
insert into hotelmanagement.manager 
		values('admin','admin','admin','111111111111111111','11111111111','经理');
#插入黑名单用户记录
insert into hotelmanagement.user(phone,name,password,level) 
		values('11111111110','user0','user0',0);
#插入普通用户记录
insert into hotelmanagement.user(phone,name,password,level) 
		values('11111111111','user1','user1',1);
#插入会员用户记录
insert into hotelmanagement.user(phone,name,password,level) 
		values('11111111112','user2','user2',2);
#插入高级会员用户记录
insert into hotelmanagement.user(phone,name,password,level) 
		values('11111111113','user3','user3',3);
#插入服务员记录
insert into hotelmanagement.waiter(account,password,name,idNumber,phone,address) 
		values('2222222220','waiter0','waiter0','222222222222222220','22222222220','军工路516号');
#插入房间记录
#单人间
insert into hotelmanagement.room(roomId,roomType,state) 
		values('0201',1,'0');
insert into hotelmanagement.room(roomId,roomType,state) 
		values('0202',1,'0');
insert into hotelmanagement.room(roomId,roomType,state) 
		values('0203',1,'0');
insert into hotelmanagement.room(roomId,roomType,state) 
		values('0204',1,'0');
#标准间
#普通
insert into hotelmanagement.room(roomId,roomType,state) 
		values('0301',2,'0');
insert into hotelmanagement.room(roomId,roomType,state) 
		values('0302',2,'0');
#豪华
insert into hotelmanagement.room(roomId,roomType,state) 
		values('0303',3,'0');
insert into hotelmanagement.room(roomId,roomType,state) 
		values('0304',3,'0');
#行政房
insert into hotelmanagement.room(roomId,roomType,state) 
		values('0401',4,'0');
insert into hotelmanagement.room(roomId,roomType,state) 
		values('0402',4,'0');
insert into hotelmanagement.room(roomId,roomType,state) 
		values('0403',4,'0');
#家庭房
insert into hotelmanagement.room(roomId,roomType,state) 
		values('0501',5,'0');  
insert into hotelmanagement.room(roomId,roomType,state) 
		values('0502',5,'0');
#双人床套间
insert into hotelmanagement.room(roomId,roomType,state) 
		values('0601',6,'0');  
insert into hotelmanagement.room(roomId,roomType,state) 
		values('0602',6,'0');
#单人床套间
insert into hotelmanagement.room(roomId,roomType,state) 
		values('0603',7,'0');  
insert into hotelmanagement.room(roomId,roomType,state) 
		values('0604',7,'0');
insert into hotelmanagement.room(roomId,roomType,state) 
		values('0605',7,'0');  
insert into hotelmanagement.room(roomId,roomType,state) 
		values('0606',7,'0');
 
#类型表里添加数据
insert into hotelmanagement.roomtype
		values(01,'单人间','128.00',18,1,1,'0','0');
insert into hotelmanagement.roomtype
		values(02,'普通标准间','218.00',20,2,2,'0','0');
insert into hotelmanagement.roomtype
		values(03,'豪华标准间','268.00',25,2,1,'1','1');
insert into hotelmanagement.roomtype
		values(04,'行政房','458.00',30,2,1,'1','1');
insert into hotelmanagement.roomtype
		values(05,'家庭房','458.00',35,3,2,'1','1');
insert into hotelmanagement.roomtype
		values(06,'双人床套间','658.00',50,4,2,'1','1');
insert into hotelmanagement.roomtype
		values(07,'单人床套间','658.00',50,4,4,'1','1');


#修改房间数量        
update roomType set typeNumber=4 where typeName='单人间';
update roomType set typeNumber=2 where typeName='普通标准间';
update roomType set typeNumber=2 where typeName='豪华标准间';
update roomType set typeNumber=3 where typeName='行政房';
update roomType set typeNumber=2 where typeName='家庭房';
update roomType set typeNumber=2 where typeName='双人床套间';
update roomType set typeNumber=4 where typeName='单人床套间';