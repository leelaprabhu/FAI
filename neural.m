function [error,xxx] = neural(X,y)
   net= feedforwardnet(20);
   net.performParam.regularization = 0.2;
   yn=y-5;
   net.inputs{1}.processFcns={}
   net.outputs{2}.processFcns={}
   net = train(net,X',yn');
   error=sqrt(sum((round(net(X'))-y').^2))/length(y);
   ff=round(net(X'))-y';
   error2=(sum(ff(find(ff>0)))*10+sum(ff(find(ff<0))))/length(y)
   plot(y,round(net(X')),'r*',[1:45],[1:45],'b-')
end
    