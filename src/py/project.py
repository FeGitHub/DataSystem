# -*- coding: utf-8 -*-

import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
import matplotlib.pyplot as plt
from sklearn.externals import joblib
from sys import argv

# 使用的特征列
FEAT_COLS = ['NCEE', 'readtime', 'wordtime']


def main():
    """
        主函数
    """
    
    RES_PATH=argv[1]
    #资源文件
    DATA_FILE=RES_PATH+"\\project.csv"
    #训练模型
    MODEL_FILE=argv[2]+"\\project_model.m"

    grade_data = pd.read_csv(DATA_FILE, usecols=FEAT_COLS + ['result'])
    plot_feat_and_grade(grade_data)

    X = grade_data[FEAT_COLS].values
    y = grade_data['result'].values

    # 分割数据集
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=1 / 3, random_state=10)

    # 建立线性回归模型
    linear_reg_model = LinearRegression()
    # 模型训练
    linear_reg_model.fit(X_train, y_train)
    joblib.dump(linear_reg_model, MODEL_FILE)
    # 验证模型
    r2_score = linear_reg_model.score(X_test, y_test)
    print('模型的R2值', r2_score)

    # 单个样本房价预测
    i = 0
    # 第一行所有列（即一条数据的特征数据）
    single_test_feat = X_test[i, :]
    y_true = y_test[i]
    y_pred = linear_reg_model.predict([single_test_feat])
    print('样本特征:', single_test_feat)
    print('真实价格：{}，预测成绩：{}'.format(y_true, y_pred))


def plot_feat_and_grade(grade_data):
    """
     用于以图表形式展示各特征数据与结果数据之间的线性关系
    :param grade_data:
    :return:
    """
    feat_cols = ['NCEE', 'readtime', 'wordtime']
    fig, axes = plt.subplots(1, 3, figsize=(20, 5))
    for i, feat_col in enumerate(feat_cols):
        grade_data[[feat_col, 'result']].plot.scatter(x=feat_col, y='result', alpha=0.5,
                                                      ax=axes[i - 3 * int(i / 3)])
    plt.tight_layout()
    plt.savefig('./grade_feat.png')
    plt.show()


def load_and_predict(single_test_feat):
    """
     用于加载已有的训练模型，并预测
    """
    linear_reg_model = joblib.load(MODEL_FILE)
    y_pred = linear_reg_model.predict([single_test_feat])
    print('预测:', y_pred)


if __name__ == '__main__':
     main()
   # load_and_predict([70, 1, 0])
