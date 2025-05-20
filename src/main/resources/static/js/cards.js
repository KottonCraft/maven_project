import { showToast } from './toast.js';
// 卡片管理相关脚本
document.addEventListener('DOMContentLoaded', function() {
    // 删除操作确认处理
    document.querySelectorAll('.delete-btn').forEach(button => {
        button.addEventListener('click', function() {
            const cardId = this.dataset.cardId;
            const formId = `deleteForm-${cardId}`;
            if (confirm(`确定要删除名片 #${cardId} 吗？此操作不可撤销！`)) {

                document.getElementById(`${formId}`).submit();
            }
        });
    });

document.querySelectorAll('.edit-btn').forEach(button => {
  button.addEventListener('click', function() {
    const form = document.getElementById('editForm');
    const modal = new bootstrap.Modal('#editModal');

    // 填充数据到表单
    form.querySelector('[name="cardName"]').value = this.dataset.cardName;
    form.querySelector('[name="cardTitle"]').value = this.dataset.cardTitle;
    form.querySelector('[name="company"]').value = this.dataset.company;
    form.querySelector('[name="position"]').value = this.dataset.position;
    form.querySelector('[name="userPhone"]').value = this.dataset.userPhone;
    form.querySelector('[name="isActive"]').checked = this.dataset.isActive === '1';

    // 更新表单提交地址
    const cardId = this.dataset.cardId;
    form.action = `/ecard/cards/update/${cardId}`;
    modal.show();
  });
});

// 处理表单提交
document.getElementById('editForm').addEventListener('submit', async (e) => {
  e.preventDefault();

  const form = e.target;
  const cardId = form.action.split('/').pop();
  const formData = new FormData(form);
  console.debug('Submitting form data:', Object.fromEntries(formData));
  try {
    const response = await fetch(form.action, {
      method: 'POST',
      headers: {
              'Content-Type': 'application/json' // 关键：添加JSON内容类型头
            },
      body: JSON.stringify(Object.fromEntries(formData))
    });

    if (response.ok) {
      window.location.reload(); // 刷新列表
    } else {
      const error = await response.json();
      alert(`更新失败：${error.message || JSON.stringify(error)}`);
    }
  } catch (err) {
    console.error('更新失败:', err);
    alert('更新操作失败，请检查网络连接');
  }
});

// 新建表单提交处理
document.getElementById('createForm')?.addEventListener('submit', async (e) => {
  e.preventDefault();

  const form = e.target;
  const formData = new FormData(form);
  const submitBtn = form.querySelector('button[type="submit"]');
  // 处理复选框状态
  const isActive = formData.get('isActive') ? '1' : '0';

  // 构建请求数据
  const data = {
    cardName: formData.get('cardName'),
    cardTitle: formData.get('cardTitle'),
    company: formData.get('company'),
    position: formData.get('position'),
    isActive: isActive
  };

  try {
    submitBtn.disabled = true; // 禁用提交按钮
    const response = await fetch(form.action, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
//        'X-CSRF-TOKEN': document.querySelector('input[name="_csrf"]').value
      },
      body: JSON.stringify(data)
    });

    const result = await response.json();
    console.debug(JSON.stringify(result))
    if (response.ok) {
        // 成功处理
      form.reset(); // 清空表单
      showToast('创建成功', {
          type: 'success',
          container: document.getElementById('createModal'),
          positionType: 'absolute'
      });
    } else {
        showToast(`创建失败：${result.message}`, {
          type: 'danger',
          container: document.getElementById('createModal'),
          positionType: 'absolute'
        });
    }
  } catch (err) {
    console.error('创建请求失败:', err);
    showToast("网络连接异常，请稍后重试", {
      type: 'danger',
      container: document.getElementById('createModal'),
      positionType: 'absolute'
    });
  } finally {
       submitBtn.disabled = false; // 恢复按钮
     }
});

// 每次打开新建模态框时重置表单
document.getElementById('createModal')?.addEventListener('show.bs.modal', () => {
  document.getElementById('createForm').reset();
});


// 显示表单错误
function showFormErrors(errors) {
  Object.entries(errors).forEach(([field, message]) => {
    const input = document.querySelector(`[name="${field}"]`);

    // 添加空值检查
    if (!input) {
      console.error(`字段 ${field} 不存在于表单中`);
      return;
    }

    // 清理旧错误
    const existingFeedback = input.nextElementSibling;
    if (existingFeedback && existingFeedback.classList.contains('invalid-feedback')) {
      existingFeedback.remove();
    }

    // 添加新错误提示
    const feedback = document.createElement('div');
    feedback.className = 'invalid-feedback';
    feedback.textContent = message;
    input.classList.add('is-invalid');
    input.after(feedback);
  });
}
});