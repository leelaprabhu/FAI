[N, p] = size(X);
ii=randperm(N);
Xts= X(ii(round(N*0.9)+1:N),:);
yts= y(ii(round(N*0.9)+1:N),:);
Xtr= X(ii(1:round(N*0.9)),:);
ytr= y(ii(1:round(N*0.9)),:)-5;

Ntr=size(Xtr,1);

sig = norm(Xtr(ceil(rand*Ntr),:)-Xtr(ceil(rand*Ntr),:));
K=300;
%[IDX,C] = kmeans(Xtr, K)
jj=randperm(N);
C=[]
C=zeros(K,p);
C=X(jj(1:300),:);
A=[]
A=zeros(Ntr,K);
for i=1:Ntr
for j=1:K
A(i,j)=exp(-norm(Xtr(i,:) - C(j,:))/sig^2);
end
end

lambda = A \ ytr;

Nts=size(Xts,1);
yh = zeros(Nts,1);
u = zeros(K,1);
for n=1:Nts
for j=1:K
u(j) = exp(-norm(Xts(n,:) - C(j,:))/sig^2);
end
yh(n) = lambda'*u;
end
plot(yts, round(yh), 'rx', 'LineWidth', 2), grid on
hold on
plot([0 45],[0 45],'b-')
title('RBF Prediction on Training Data', 'FontSize', 16);
xlabel('Target', 'FontSize', 14);
ylabel('Prediction', 'FontSize', 14);
error=sqrt(sum((yts-floor(yh)).^2))/length(yts)
ff=floor(yh)-yts;
error2=(sum(ff(find(ff>0)))*10-sum(ff(find(ff<0))))/length(yts)