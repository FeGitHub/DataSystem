# -*- coding: utf-8 -*-

import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
import matplotlib.pyplot as plt
from sklearn.externals import joblib
from sys import argv

# 使用的特征列
FEAT_COLS = []
# 结果列
RESULTCOL = []
#资源文件
RES_PATH = ""
#模型文件
MODEL_FILE = ""

"""
----------------------------------------
-------通用逻辑回归算法-----------------
- 参数要求：
-       1：参数1为待分析资源文件路径(csv文件)
-       2：参数2为模型文件存放位置
-       3：参数3为文件资源的头部标题
-       4：
----------------------------------------
"""



def build_param():
    """
    构建参数
    """
    global RES_PATH
    #读取列信息
    global MODEL_FILE
    global FEAT_COLS
    global RESULTCOL
    RES_PATH = argv[1]
    MODEL_FILE = argv[2]
    heads=argv[3]
    FEAT_COLS = heads.split(',')
    RESULTCOL = FEAT_COLS[-1] 
    del FEAT_COLS[-1]
    #print("RES_PATH:",RES_PATH)
    #print("MODEL_FILE:",MODEL_FILE)
    print("特征数据列:",FEAT_COLS)
    print("结果列:",RESULTCOL)

def show_feat(csv_data):
    """
       特征列和结果列的关系
    """
    for feat in FEAT_COLS:
        X = csv_data[feat].values.reshape(-1, 1)
        y = csv_data[RESULTCOL].values
        X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=1 / 3, random_state=10)
        linear_reg_model = LinearRegression()
        linear_reg_model.fit(X_train, y_train)
        r2_score = linear_reg_model.score(X_test, y_test)
        print('特征：{}，R2值：{}'.format(feat, r2_score))


def main():
    """
        主函数
    """
    #待生成的训练模型
    csv_data = pd.read_csv(RES_PATH, usecols=FEAT_COLS + [RESULTCOL])
    show_feat(csv_data)
    X = csv_data[FEAT_COLS].values
    y = csv_data[RESULTCOL].values

    # 分割数据集
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=1 / 3, random_state=10)

    # 建立线性回归模型
    linear_reg_model = LinearRegression()
    
    # 模型训练
    linear_reg_model.fit(X_train, y_train) 
    joblib.dump(linear_reg_model, MODEL_FILE)
     
    score = linear_reg_model.score(X_test, y_test)
    print('模型的R2值', score)
    
    i = 0
    # 第i行所有列（即一条数据的特征数据）
    single_test_feat = X_test[i, :]
    y_true = y_test[i]
    y_pred = linear_reg_model.predict([single_test_feat])
    print('样本特征:', single_test_feat)
    print('真实结果数据：{}，预测结果数据：{}'.format(y_true, y_pred))
    print(FEAT_COLS,"拟合函数对应权重如下依次对应，截距为最后一行参数:")
    print(linear_reg_model.coef_)  
    print(linear_reg_model.intercept_)



if __name__ == '__main__':
     build_param()
     main()


