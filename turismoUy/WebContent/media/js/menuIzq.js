$('.open-btn').on('click', function(){
    if ($('.sidebar').hasClass('active'))
        $('.sidebar').removeClass('active');
    else
        $('.sidebar').addClass('active');
})